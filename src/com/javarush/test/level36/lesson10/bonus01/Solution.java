package com.javarush.test.level36.lesson10.bonus01;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("C:\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        try {
            if (!(packageName.endsWith("/") || packageName.endsWith("\\"))) {
                packageName = packageName + "/";
            }

            File directory = new File(packageName);
            String[] classFiles = directory.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".class");
                }
            });

            final String path = packageName;

            ClassLoader loader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    try {
                        byte[] data = Files.readAllBytes(Paths.get(path + name));
                        return defineClass(null, data, 0, data.length);
                    } catch (Exception e) {
                        return null;
                    }
                }
            };

            for (String className : classFiles) {
                Class c = loader.loadClass(className);
                hiddenClasses.add(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        try {
            for (Class c : hiddenClasses) {
                if (c.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    Constructor defaultConstructor = c.getDeclaredConstructor();
                    defaultConstructor.setAccessible(true);
                    return (HiddenClass)defaultConstructor.newInstance();
                }
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}