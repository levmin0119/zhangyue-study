package jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 类加载器的加载流程实例代码
 */
public class ClassLoadDemo {
    private static int[] array = new int[3];

    private static int length = array.length;

    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;


    public static void main(String[] args) throws Exception {
//        One oneObject = one.newInstance();
        One oneObject = new ClassLoadDemo().new One("initial value");
//        Constructor<One> constructor = one.getConstructor();
//        One oneObject = constructor.newInstance();
        oneObject.call();

        Another anotherObject = new ClassLoadDemo().new Another();
        anotherObject.speak();

        //通过类获取私有成员属性对象Filed
        Field inner = one.getDeclaredField("inner");

        inner.setAccessible(true);

        inner.set(oneObject, "world change");

        System.out.println(oneObject.getInner());


        ClassLoader classLoader = ClassLoadDemo.class.getClassLoader();
        ClassLoader parent = classLoader.getParent();
        ClassLoader parent1 = parent.getParent();

        System.out.println(classLoader);
        System.out.println(parent);
        System.out.println(parent1);
    }


    class One {
        public One(String inner) {
            this.inner = inner;
        }

        private String inner = "time files.";

        public void call() {
            System.out.println("Hello world!");

        }

        public String getInner() {
            return inner;
        }
    }

    class Another {
        public void speak() {

            HashMap<String, Object> stringObjectHashMap = new HashMap<>();

            System.out.println("easy Coding!");

            HashSet hashSet = new HashSet<String>();

            hashSet.add("1");

            hashSet.add("2");

        }
    }
}
