package qarma64

import chisel3._
import chisel3.util._





class QARMA64() extends Module {

val io = IO(new Bundle {
    // Based off of the python implementation given in 
    // qarma64(plaintext, tweak, key, encryption=True, rounds=5):

    //Inputs
    val plaintext = Input(UInt(64.W)) //64 bits / 8 bytes
    val tweak = Input(UInt(64.W)) //64 bits
    val key = Input(UInt(128.W)) // w0+k0 each 64 bits -> this is concatenated strings in python which is sliced to get the individual values 
    val encryption = Input(Bool()) //true or flase -> true by default -> how do we handle that? 
    val rounds = Input(UInt(16.W)) // 

    //Outputs
    val cipher = Output(UInt(64.W)) //output cipher which is 64 bits

  }) //input bundle ends here
    
    //Defining the sboxes in vector variables
    //sbox0 = [0,14,2,10,9,15,8,11,6,4,3,7,13,12,1,5] # sbox 0: lightest version, fixed points at 0,2
    //sbox1 = [10,13,14,6,15,7,3,5,9,8,0,12,11,1,2,4] # sbox 1: no fixed points
    //sbox2 = [11,6,8,15,12,0,9,14,3,7,4,5,13,2,1,10] # sbox 2: lightweight sbox from prince family

    val sbox0 = VecInit(0.U(4.W), 14.U(4.W), 2.U(4.W), 10.U(4.W), 9.U(4.W), 15.U(4.W), 8.U(4.W), 11.U(4.W), 6.U(4.W), 4.U(4.W), 3.U(4.W), 7.U(4.W), 13.U(4.W), 12.U(4.W), 1.U(4.W), 5.U(4.W))
    val sbox1 = VecInit(10.U(4.W), 13.U(4.W), 14.U(4.W), 6.U(4.W), 15.U(4.W), 7.U(4.W), 3.U(4.W), 5.U(4.W), 9.U(4.W), 8.U(4.W), 0.U(4.W), 12.U(4.W), 11.U(4.W), 1.U(4.W), 2.U(4.W), 4.U(4.W))
    val sbox2 = VecInit(11.U(4.W), 6.U(4.W), 8.U(4.W), 15.U(4.W), 12.U(4.W), 0.U(4.W), 9.U(4.W), 14.U(4.W), 3.U(4.W), 7.U(4.W), 4.U(4.W), 5.U(4.W), 13.U(4.W), 2.U(4.W), 1.U(4.W), 10.U(4.W))

    //State and tweak permutations
    val state_permutation = VecInit(0.U(4.W), 11.U(4.W), 6.U(4.W), 13.U(4.W), 10.U(4.W), 1.U(4.W), 12.U(4.W), 7.U(4.W), 5.U(4.W), 14.U(4.W), 3.U(4.W), 8.U(4.W), 15.U(4.W), 4.U(4.W), 9.U(4.W), 2.U(4.W))
    val state_permutation_inv = VecInit(0.U(4.W), 5.U(4.W), 15.U(4.W), 10.U(4.W), 13.U(4.W), 8.U(4.W), 2.U(4.W), 7.U(4.W), 11.U(4.W), 14.U(4.W), 4.U(4.W), 1.U(4.W), 6.U(4.W), 3.U(4.W), 9.U(4.W), 12.U(4.W))
    val tweak_permutation = VecInit(6.U(4.W), 5.U(4.W), 14.U(4.W), 15.U(4.W), 0.U(4.W), 1.U(4.W), 2.U(4.W), 3.U(4.W), 7.U(4.W), 12.U(4.W), 13.U(4.W), 4.U(4.W), 8.U(4.W), 9.U(4.W), 10.U(4.W), 11.U(4.W))

    io.cipher := RegNext(io.plaintext) //dummy output just to test
}