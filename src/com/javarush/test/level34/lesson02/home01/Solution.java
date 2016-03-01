package com.javarush.test.level34.lesson02.home01;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recursion(final String expression, int countOperation) {
        Locale.setDefault(Locale.ENGLISH);
        Pattern numberPattern = Pattern.compile("^(\\d+\\.?\\d*).*");
        Pattern openBracketPattern = Pattern.compile("^\\(.*");
        Pattern addPattern = Pattern.compile("^(\\+).*");
        Pattern subPattern = Pattern.compile("^(\\-).*");
        Pattern divPattern = Pattern.compile("^(/).*");
        Pattern mulPattern = Pattern.compile("^(\\*).*");
        Pattern powPattern = Pattern.compile("^(\\^).*");
        Pattern sinPattern = Pattern.compile("^(sin\\().*");
        Pattern cosPattern = Pattern.compile("^(cos\\().*");
        Pattern tanPattern = Pattern.compile("^(tan\\().*");
        boolean firstScan = countOperation==0;
        String tail = expression;
        if(firstScan)
        {
            tail = expression.replace(" ", "").toLowerCase();
        }
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<Integer> priorities = new ArrayList<>();
        while (tail.length()>0)
        {
            Matcher m;
            m = numberPattern.matcher(tail);
            if(m.find())
            {
                String piece = m.group(1);
                tokens.add(piece);
                tail = tail.substring(piece.length());
                priorities.add(0);
                continue;
            }
            m = openBracketPattern.matcher(tail);
            if (m.find())
            {
                int end = findCloseBracket(tail, 0);
                String piece = tail.substring(0, end);
                tokens.add( piece );
                tail = tail.substring(piece.length());
                priorities.add(0);
                continue;
            }
            m = addPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                tokens.add( piece );
                tail = tail.substring(piece.length());
                priorities.add(4);
                countOperation++;
                continue;
            }
            m = subPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                if(tokens.size()==0)
                {
                    tokens.add( "0" );
                    priorities.add(0);
                }
                tokens.add( piece );
                tail = tail.substring(piece.length());
                priorities.add(4);
                countOperation++;
                continue;
            }
            m = divPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                tokens.add( piece );
                tail = tail.substring(piece.length());
                priorities.add(3);
                countOperation++;
                continue;
            }
            m = mulPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                tokens.add( piece );
                tail = tail.substring(piece.length());
                priorities.add(3);
                countOperation++;
                continue;
            }
            m = powPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                tokens.add( piece );
                tail = tail.substring(piece.length());
                priorities.add(2);
                countOperation++;
                continue;
            }
            m = sinPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                tokens.add( piece );
                tail = tail.substring(piece.length()-1);
                priorities.add(1);
                countOperation++;
                continue;
            }
            m = cosPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                tokens.add( piece );
                tail = tail.substring(piece.length()-1);
                priorities.add(1);
                countOperation++;
                continue;
            }
            m = tanPattern.matcher(tail);
            if (m.find())
            {
                String piece = m.group(1);
                tokens.add( piece );
                tail = tail.substring(piece.length()-1);
                priorities.add(1);
                countOperation++;
                continue;
            }
            throw new IllegalArgumentException(String.format("Can't parse expression '%s'", tail));
        }
        int minPriority=0, maxPriority=0;
        for (int p: priorities)
        {
            if(p<minPriority) minPriority = p;
            if(p>maxPriority) maxPriority = p;
        }
        ArrayList<Integer> parents = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) parents.add(-1);
        ArrayList<Double> results = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) results.add(0d);
        int last = 0;
        for(int i=minPriority; i<=maxPriority; i++ )
        {
            for (int j = 0; j < tokens.size(); j++)
            {
                if(priorities.get(j) != i) continue;
                String token = tokens.get(j);
                if(numberPattern.matcher(token).matches())
                {
                    results.set(j, Double.parseDouble(token));
                    last = j;
                    continue;
                }
                if (openBracketPattern.matcher(token).matches())
                {
                    PrintStream oldOut = System.out;
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    PrintStream stream = new PrintStream(outputStream);
                    System.setOut(stream);
                    recursion(token.substring(1, token.length() - 1), countOperation);
                    System.setOut(oldOut);
                    String resultString = outputStream.toString();
                    String[] resultArray = resultString.trim().split(" ");
                    results.set(j, Double.parseDouble(resultArray[0]));
                    countOperation = Integer.parseInt(resultArray[1]);
                    last = j;
                    continue;
                }
                if (addPattern.matcher(token).matches())
                {
                    double left = results.get( findAndSetParent(j, j-1, parents) );
                    double right = results.get( findAndSetParent(j, j+1, parents) );
                    results.set(j, left+right);
                    last = j;
                    continue;
                }
                if (subPattern.matcher(token).matches())
                {
                    double left = results.get( findAndSetParent(j, j-1, parents) );
                    double right = results.get( findAndSetParent(j, j+1, parents) );
                    results.set(j, left-right);
                    last = j;
                    continue;
                }
                if (mulPattern.matcher(token).matches())
                {
                    double left = results.get( findAndSetParent(j, j-1, parents) );
                    double right = results.get( findAndSetParent(j, j+1, parents) );
                    results.set(j, left*right);
                    last = j;
                    continue;
                }
                if (divPattern.matcher(token).matches())
                {
                    double left = results.get( findAndSetParent(j, j-1, parents) );
                    double right = results.get( findAndSetParent(j, j+1, parents) );
                    results.set(j, left/right);
                    last = j;
                    continue;
                }
                if (powPattern.matcher(token).matches())
                {
                    double left = results.get( findAndSetParent(j, j-1, parents) );
                    double right = results.get( findAndSetParent(j, j+1, parents) );
                    results.set(j, Math.pow(left,right));
                    last = j;
                    continue;
                }
                if (sinPattern.matcher(token).matches())
                {
                    double right = results.get( j+1 );
                    parents.set(j+1, j);
                    results.set(j, Math.sin( Math.toRadians(right)));
                    last = j;
                    continue;
                }
                if (cosPattern.matcher(token).matches())
                {
                    double right = results.get( j+1 );
                    parents.set(j+1, j);
                    results.set(j, Math.cos( Math.toRadians(right)));
                    last = j;
                    continue;
                }
                if (tanPattern.matcher(token).matches())
                {
                    double right = results.get( j+1 );
                    parents.set(j+1, j);
                    results.set(j, Math.tan( Math.toRadians(right)));
                    last = j;
                    continue;
                }
            }
        }
        DecimalFormat df = firstScan? new DecimalFormat("#.##") :new DecimalFormat("#.########");
        System.out.format("%s %d\n", df.format(results.get(last)), countOperation);
    }


    public int findAndSetParent(int p, int i, ArrayList<Integer> parents)
    {
        while (parents.get(i)!=-1) i=parents.get(i);
        parents.set(i, p);
        return i;
    }
    public int findCloseBracket(String source, int from)
    {
        int pos = from, deep=0;
        do
        {
            if(source.charAt(pos)=='(') deep++;
            if(source.charAt(pos)==')') deep--;
            pos++;
        }while (deep>0);
        return pos;
    }
    public Solution() {
    }
}