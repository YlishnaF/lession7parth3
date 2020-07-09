public class TestClass  {
    @BeforeSuite
    public static void cook(){
        System.out.println("Мама приготовила еду");
    }
    public static void cook2(){
        System.out.println("Мама приготовила еду");
    }

    @Test
    public static void eat2(){
        System.out.println("Мама положила себе еду. Поела.");
    }
    @Test(priority = 7)
    public static void eat3(){
        System.out.println("Сын положил себе еду. Поел.");
    }
    @AfterSuite
    public static void clean(){
        System.out.println("Сын вымыл посуду!");
    }
    @Test(priority = 3)
    public static void eat1(){
        System.out.println("Отец положил себе еду. Поел.");
    }


}
