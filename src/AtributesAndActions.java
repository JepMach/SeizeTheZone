public interface AtributesAndActions {
    // Atributes


    //Actions
    default int getMoveCost(){
        return 1;
    }
    default void tackle(){

    }
    default int tackleCost(){
        return 2;
    }
}
