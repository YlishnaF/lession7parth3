import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class testClass = TestClass.class;
        start(testClass);


    }
    public static void start (Class testClass) throws InvocationTargetException, IllegalAccessException {
        Method [] methods = testClass.getDeclaredMethods();
        Method [] sortMethods = new Method[methods.length];
        int k = 0;



        for(Method m: methods){

            for (int i = 1; i < 11; i++) {
                if(m.isAnnotationPresent(Test.class) && (m.getAnnotation(Test.class).priority() == i)){
                    System.out.println(m.getAnnotation(Test.class).priority());
                    sortMethods [k] = m;
                    k++;
                } else if(m.isAnnotationPresent(AfterSuite.class) && (m.getAnnotation(AfterSuite.class).priority() == i)){
                    System.out.println(m.getAnnotation(AfterSuite.class).priority());
                    sortMethods [k] = m;
                    k++;
                }else if(m.isAnnotationPresent(BeforeSuite.class) && (m.getAnnotation(BeforeSuite.class).priority() == i)){
                    System.out.println(m.getAnnotation(BeforeSuite.class).priority());

                    sortMethods [k] = m;
                    k++;
                }

            }


        }
        System.out.println(Arrays.toString(sortMethods));
        System.out.println(k);

        for(Method method: sortMethods){
            if(method.isAnnotationPresent(Test.class)){
                method.invoke(null);
            }
            if(method.isAnnotationPresent(BeforeSuite.class)){
                method.invoke(null);
            }
            if(method.isAnnotationPresent(AfterSuite.class)){
                method.invoke(null);
            }
        }

    }
}
