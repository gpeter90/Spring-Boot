package hu.webuni.exam.logistics.exception;

public class NoValidMileStoneToTranportPlanException extends RuntimeException{

    public NoValidMileStoneToTranportPlanException() {
        super("MileStone is not valid to this transportplan!");
    }
}
