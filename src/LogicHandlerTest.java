import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogicHandlerTest {

    public LogicHandlerTest(){

    }

    @Test
    public void testMethod(){
        LogicHandler testHandler = new LogicHandler();

        QuarterBack testQB = new QuarterBack();
        WideReciever testWR= new WideReciever();

        testHandler.addBrikker(testQB,testWR);

        for(Brik brik: testHandler.brikkerList) {
            System.out.println(brik.name);
            testHandler.handleObjectBrik(brik,4);
        }



    }

}
