package com.example.quoters;

import jakarta.annotation.PostConstruct;


import java.util.Objects;

@Profiling
@DeprecatedClass(newImplementation = T1000.class)
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    private String message;

    public TerminatorQuoter() {

        System.out.println("Phase 1");
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("Phase 2");
        System.out.println(repeat);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println("The Terminator says: " + message);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(repeat, message);
    }
}
