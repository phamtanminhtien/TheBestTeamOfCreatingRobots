package com.tietha.thebestteamofcreatingrobots;

public class Car {
    private boolean F,B,R,L;
    private byte status;

    public Car() {
        F = false;
        B = false;
        R = false;
        L = false;
    }

    public void ForWardDown(){
        this.F = true;
        GetStatus();
    }
    public void ForWardUp(){
        this.F = false;
        GetStatus();
    }
    public void BackDown(){
        this.B = true;
        GetStatus();
    }
    public void BackUp(){
        this.B = false;
        GetStatus();
    }
    public void LeftDown(){
        this.L = true;
        GetStatus();
    }
    public void LeftUp(){
        this.L = false;
        GetStatus();
    }
    public void RightDown(){
        this.R = true;
        GetStatus();
    }
    public void RightUp(){
        this.R = false;
        GetStatus();
    }
    public byte GetStatus(){
        this.status = 'F';
        if(this.F == true && this.R == false && this.L == false){
            this.status = 'F';
        }
        if(this.B == true && this.R == false && this.L == false){
            this.status = 'B';
        }
        if(this.R == true && this.F == false && this.B ==false){
            this.status = 'R';
        }
        if(this.L == true && this.F == false && this.B ==false){
            this.status = 'L';
        }
        if(this.F == true && this.R == true){
            this.status ='I';
        }
        if(this.F == true && this.L == true){
            this.status ='G';
        }
        if(this.B == true && this.R == true){
            this.status ='J';
        }
        if(this.B == true && this.L == true){
            this.status ='H';
        }
        if(this.B == false && this.F == false && this.L == false && this.R == false){
            this.status = 'S';
        }
        return this.status;
    }

}
