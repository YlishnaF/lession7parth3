import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) throws RuntimeException, InvocationTargetException, IllegalAccessException {
        Class testClass = TestClass.class;
        start(testClass);


    }
    public static void start (Class testClass) throws RuntimeException, InvocationTargetException, IllegalAccessException {
        Method [] methods = testClass.getDeclaredMethods();
//        List<Method> sortMethod = new ArrayList<Method>();
        Method [] sortMethods = new Method[methods.length];
        int afterSuit=0;
        int beforeSuit=0;
        int k = 0;
        for(Method m: methods){


            for (int i = 1; i < 11; i++) {
                if(m.isAnnotationPresent(Test.class) && (m.getAnnotation(Test.class).priority() == i)){
                    System.out.println( "Приоритет метода " + m.getAnnotation(Test.class).priority());
                    sortMethods [k] = m;
                    k++;
                } else if(m.isAnnotationPresent(AfterSuite.class) && (m.getAnnotation(AfterSuite.class).priority() == i)){

                    System.out.println("Приоритет метода " + m.getAnnotation(AfterSuite.class).priority());
                    sortMethods [k] = m;
                    k++;
                }else if(m.isAnnotationPresent(BeforeSuite.class) && (m.getAnnotation(BeforeSuite.class).priority() == i)){
                    System.out.println("Приоритет метода " + m.getAnnotation(BeforeSuite.class).priority());


                    sortMethods [k] = m;
                    k++;
                }

            }


        }
        System.out.println(Arrays.toString(sortMethods));


        for(Method method: sortMethods){
            if(method.isAnnotationPresent(Test.class)){
                method.invoke(null);
            }
            if(method.isAnnotationPresent(BeforeSuite.class)){
                beforeSuit++;
                if(beforeSuit>1){
                    throw new RuntimeException("BeforeSuite не может быть больше одного метода");
                }
                method.invoke(null);
            }
            if(method.isAnnotationPresent(AfterSuite.class)){
                afterSuit++;
                if(afterSuit>1){
                    throw new RuntimeException("AfterSuit не может быть больше одного метода");
                }
                method.invoke(null);
            }
        }


// Не удалось отсортировать через компоратор!
//        for (int i = 0; i < methods.length ; i++) {
//            sortMethod.add(methods[i]);
//
//        }

//        Collections.sort(sortMethod, new Comparator<Method>() {
//            @Override
//            public int compare(Method o1, Method o2) {
//                if(o1.getAnnotation(Test.class).priority() > o2.getAnnotation(Test.class).priority() || o1.getAnnotation(AfterSuite.class).priority() > o2.getAnnotation(Test.class).priority() || o1.getAnnotation(BeforeSuite.class).priority() > o2.getAnnotation(Test.class).priority() ||
//                        o1.getAnnotation(Test.class).priority() > o2.getAnnotation(AfterSuite.class).priority() || o1.getAnnotation(Test.class).priority() > o2.getAnnotation(BeforeSuite.class).priority() || o1.getAnnotation(BeforeSuite.class).priority() > o2.getAnnotation(BeforeSuite.class).priority() || o1.getAnnotation(AfterSuite.class).priority() > o2.getAnnotation(AfterSuite.class).priority() ) {
//                    return 1;
//                } else{
//                    return  -1;
//                }
//            }
//        });

//        for(Method method: sortMethod){
//            if(method.isAnnotationPresent(Test.class)){
//                method.invoke(null);
//            }
//            if(method.isAnnotationPresent(BeforeSuite.class)){
//                beforeSuit++;
//                if(beforeSuit>1){
//                    throw new RuntimeException("BeforeSuite не может быть больше одного метода");
//                }
//                method.invoke(null);
//            }
//            if(method.isAnnotationPresent(AfterSuite.class)){
//                afterSuit++;
//                if(afterSuit>1){
//                    throw new RuntimeException("AfterSuit не может быть больше одного метода");
//                }
//                method.invoke(null);
//            }
//        }


    }
}
