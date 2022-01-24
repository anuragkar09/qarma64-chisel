//package qarma64

import chisel3._
import chisel3.util._

class CalcTweak() extends Module {
val io = IO(new Bundle {
    //Inputs
    val tweak = Input(UInt(64.W)) 
    //Outputs
    val tweak_r = Output(UInt(64.W)) 

  }) //input bundle ends here
    //experimental code

    val tweak_permutation = WireInit(VecInit(6.U(64.W), 5.U(64.W), 14.U(64.W), 15.U(64.W), 0.U(64.W), 1.U(64.W), 2.U(64.W), 3.U(64.W), 7.U(64.W), 12.U(64.W), 13.U(64.W), 4.U(64.W), 8.U(64.W), 9.U(64.W), 10.U(64.W), 11.U(64.W)))
    val block_tweak = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

    val return_value = Wire(UInt(64.W))

    val block_tweak_result_0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweak_result_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweak_result_2 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweak_result_3 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweak_result_4= WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

    val block_permuted_tweak_0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_2 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_3 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_4 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

    for (j <- 0 to 15) //hex to block
    {
        block_tweak(15-j) := ((io.tweak & ("hF".U << j*4)) >> j*4)
    }

    //printf(p"Block Tweak : $block_tweak\n")
    
        //loop iteration 1
        block_permuted_tweak_0(0) := block_tweak(tweak_permutation(0))
        block_permuted_tweak_0(1) := block_tweak(tweak_permutation(1))
        block_permuted_tweak_0(2) := block_tweak(tweak_permutation(2))
        block_permuted_tweak_0(3) := block_tweak(tweak_permutation(3))
        block_permuted_tweak_0(4) := block_tweak(tweak_permutation(4))
        block_permuted_tweak_0(5) := block_tweak(tweak_permutation(5))
        block_permuted_tweak_0(6) := block_tweak(tweak_permutation(6))
        block_permuted_tweak_0(7) := block_tweak(tweak_permutation(7))
        block_permuted_tweak_0(8) := block_tweak(tweak_permutation(8))
        block_permuted_tweak_0(9) := block_tweak(tweak_permutation(9))
        block_permuted_tweak_0(10) := block_tweak(tweak_permutation(10))
        block_permuted_tweak_0(11) := block_tweak(tweak_permutation(11))
        block_permuted_tweak_0(12) := block_tweak(tweak_permutation(12))
        block_permuted_tweak_0(13) := block_tweak(tweak_permutation(13))
        block_permuted_tweak_0(14) := block_tweak(tweak_permutation(14))
        block_permuted_tweak_0(15) := block_tweak(tweak_permutation(15))
        
        block_tweak_result_0(0):= ((((block_permuted_tweak_0(0)>>0)&1.U)^((block_permuted_tweak_0(0)>>1)&1.U))<<3) | (((block_permuted_tweak_0(0)>>3)&1.U)<<2) | (((block_permuted_tweak_0(0)>>2)&1.U)<<1) | ((block_permuted_tweak_0(0)>>1)&1.U)
        block_tweak_result_0(1):= ((((block_permuted_tweak_0(1)>>0)&1.U)^((block_permuted_tweak_0(1)>>1)&1.U))<<3) | (((block_permuted_tweak_0(1)>>3)&1.U)<<2) | (((block_permuted_tweak_0(1)>>2)&1.U)<<1) | ((block_permuted_tweak_0(1)>>1)&1.U)
        block_tweak_result_0(2):= block_permuted_tweak_0(2)
        block_tweak_result_0(3):= ((((block_permuted_tweak_0(3)>>0)&1.U)^((block_permuted_tweak_0(3)>>1)&1.U))<<3) | (((block_permuted_tweak_0(3)>>3)&1.U)<<2) | (((block_permuted_tweak_0(3)>>2)&1.U)<<1) | ((block_permuted_tweak_0(3)>>1)&1.U)
        block_tweak_result_0(4):= ((((block_permuted_tweak_0(4)>>0)&1.U)^((block_permuted_tweak_0(4)>>1)&1.U))<<3) | (((block_permuted_tweak_0(4)>>3)&1.U)<<2) | (((block_permuted_tweak_0(4)>>2)&1.U)<<1) | ((block_permuted_tweak_0(4)>>1)&1.U)
        block_tweak_result_0(5):= block_permuted_tweak_0(5)
        block_tweak_result_0(6):= block_permuted_tweak_0(6)
        block_tweak_result_0(7):= block_permuted_tweak_0(7)
        block_tweak_result_0(8):= ((((block_permuted_tweak_0(8)>>0)&1.U)^((block_permuted_tweak_0(8)>>1)&1.U))<<3) | (((block_permuted_tweak_0(8)>>3)&1.U)<<2) | (((block_permuted_tweak_0(8)>>2)&1.U)<<1) | ((block_permuted_tweak_0(8)>>1)&1.U)
        block_tweak_result_0(9):= block_permuted_tweak_0(9)
        block_tweak_result_0(10):= block_permuted_tweak_0(10)
        block_tweak_result_0(11):= ((((block_permuted_tweak_0(11)>>0)&1.U)^((block_permuted_tweak_0(11)>>1)&1.U))<<3) | (((block_permuted_tweak_0(11)>>3)&1.U)<<2) | (((block_permuted_tweak_0(11)>>2)&1.U)<<1) | ((block_permuted_tweak_0(11)>>1)&1.U)
        block_tweak_result_0(12):= block_permuted_tweak_0(12)
        block_tweak_result_0(13):= ((((block_permuted_tweak_0(13)>>0)&1.U)^((block_permuted_tweak_0(13)>>1)&1.U))<<3) | (((block_permuted_tweak_0(13)>>3)&1.U)<<2) | (((block_permuted_tweak_0(13)>>2)&1.U)<<1) | ((block_permuted_tweak_0(13)>>1)&1.U)
        block_tweak_result_0(14):= block_permuted_tweak_0(14)
        block_tweak_result_0(15):= block_permuted_tweak_0(15)

        //printf(p"End of Loop 1\n")
        //printf(p"Block Permuted Tweak 0) : $block_permuted_tweak_0\n")
        //printf(p"Block Tweak Result 0 : $block_tweak_result_0\n")
       
        //loop iteration 2
        block_permuted_tweak_1(0) := block_tweak_result_0(tweak_permutation(0))
        block_permuted_tweak_1(1) := block_tweak_result_0(tweak_permutation(1))
        block_permuted_tweak_1(2) := block_tweak_result_0(tweak_permutation(2))
        block_permuted_tweak_1(3) := block_tweak_result_0(tweak_permutation(3))
        block_permuted_tweak_1(4) := block_tweak_result_0(tweak_permutation(4))
        block_permuted_tweak_1(5) := block_tweak_result_0(tweak_permutation(5))
        block_permuted_tweak_1(6) := block_tweak_result_0(tweak_permutation(6))
        block_permuted_tweak_1(7) := block_tweak_result_0(tweak_permutation(7))
        block_permuted_tweak_1(8) := block_tweak_result_0(tweak_permutation(8))
        block_permuted_tweak_1(9) := block_tweak_result_0(tweak_permutation(9))
        block_permuted_tweak_1(10) := block_tweak_result_0(tweak_permutation(10))
        block_permuted_tweak_1(11) := block_tweak_result_0(tweak_permutation(11))
        block_permuted_tweak_1(12) := block_tweak_result_0(tweak_permutation(12))
        block_permuted_tweak_1(13) := block_tweak_result_0(tweak_permutation(13))
        block_permuted_tweak_1(14) := block_tweak_result_0(tweak_permutation(14))
        block_permuted_tweak_1(15) := block_tweak_result_0(tweak_permutation(15))
        
        block_tweak_result_1(0):= ((((block_permuted_tweak_1(0)>>0)&1.U)^((block_permuted_tweak_1(0)>>1)&1.U))<<3) | (((block_permuted_tweak_1(0)>>3)&1.U)<<2) | (((block_permuted_tweak_1(0)>>2)&1.U)<<1) | ((block_permuted_tweak_1(0)>>1)&1.U)
        block_tweak_result_1(1):= ((((block_permuted_tweak_1(1)>>0)&1.U)^((block_permuted_tweak_1(1)>>1)&1.U))<<3) | (((block_permuted_tweak_1(1)>>3)&1.U)<<2) | (((block_permuted_tweak_1(1)>>2)&1.U)<<1) | ((block_permuted_tweak_1(1)>>1)&1.U)
        block_tweak_result_1(2):= block_permuted_tweak_1(2)
        block_tweak_result_1(3):= ((((block_permuted_tweak_1(3)>>0)&1.U)^((block_permuted_tweak_1(3)>>1)&1.U))<<3) | (((block_permuted_tweak_1(3)>>3)&1.U)<<2) | (((block_permuted_tweak_1(3)>>2)&1.U)<<1) | ((block_permuted_tweak_1(3)>>1)&1.U)
        block_tweak_result_1(4):= ((((block_permuted_tweak_1(4)>>0)&1.U)^((block_permuted_tweak_1(4)>>1)&1.U))<<3) | (((block_permuted_tweak_1(4)>>3)&1.U)<<2) | (((block_permuted_tweak_1(4)>>2)&1.U)<<1) | ((block_permuted_tweak_1(4)>>1)&1.U)
        block_tweak_result_1(5):= block_permuted_tweak_1(5)
        block_tweak_result_1(6):= block_permuted_tweak_1(6)
        block_tweak_result_1(7):= block_permuted_tweak_1(7)
        block_tweak_result_1(8):= ((((block_permuted_tweak_1(8)>>0)&1.U)^((block_permuted_tweak_1(8)>>1)&1.U))<<3) | (((block_permuted_tweak_1(8)>>3)&1.U)<<2) | (((block_permuted_tweak_1(8)>>2)&1.U)<<1) | ((block_permuted_tweak_1(8)>>1)&1.U)
        block_tweak_result_1(9):= block_permuted_tweak_1(9)
        block_tweak_result_1(10):= block_permuted_tweak_1(10)
        block_tweak_result_1(11):= ((((block_permuted_tweak_1(11)>>0)&1.U)^((block_permuted_tweak_1(11)>>1)&1.U))<<3) | (((block_permuted_tweak_1(11)>>3)&1.U)<<2) | (((block_permuted_tweak_1(11)>>2)&1.U)<<1) | ((block_permuted_tweak_1(11)>>1)&1.U)
        block_tweak_result_1(12):= block_permuted_tweak_1(12)
        block_tweak_result_1(13):= ((((block_permuted_tweak_1(13)>>0)&1.U)^((block_permuted_tweak_1(13)>>1)&1.U))<<3) | (((block_permuted_tweak_1(13)>>3)&1.U)<<2) | (((block_permuted_tweak_1(13)>>2)&1.U)<<1) | ((block_permuted_tweak_1(13)>>1)&1.U)
        block_tweak_result_1(14):= block_permuted_tweak_1(14)
        block_tweak_result_1(15):= block_permuted_tweak_1(15)

        //printf(p"End of Loop 2\n")
        //printf(p"Block Permuted Tweak 1 : $block_permuted_tweak_1\n")
        //printf(p"Block Tweak Result 1: $block_tweak_result_1\n")
        

        //loop iteration 3

        block_permuted_tweak_2(0) := block_tweak_result_1(tweak_permutation(0))
        block_permuted_tweak_2(1) := block_tweak_result_1(tweak_permutation(1))
        block_permuted_tweak_2(2) := block_tweak_result_1(tweak_permutation(2))
        block_permuted_tweak_2(3) := block_tweak_result_1(tweak_permutation(3))
        block_permuted_tweak_2(4) := block_tweak_result_1(tweak_permutation(4))
        block_permuted_tweak_2(5) := block_tweak_result_1(tweak_permutation(5))
        block_permuted_tweak_2(6) := block_tweak_result_1(tweak_permutation(6))
        block_permuted_tweak_2(7) := block_tweak_result_1(tweak_permutation(7))
        block_permuted_tweak_2(8) := block_tweak_result_1(tweak_permutation(8))
        block_permuted_tweak_2(9) := block_tweak_result_1(tweak_permutation(9))
        block_permuted_tweak_2(10) := block_tweak_result_1(tweak_permutation(10))
        block_permuted_tweak_2(11) := block_tweak_result_1(tweak_permutation(11))
        block_permuted_tweak_2(12) := block_tweak_result_1(tweak_permutation(12))
        block_permuted_tweak_2(13) := block_tweak_result_1(tweak_permutation(13))
        block_permuted_tweak_2(14) := block_tweak_result_1(tweak_permutation(14))
        block_permuted_tweak_2(15) := block_tweak_result_1(tweak_permutation(15))
        
        block_tweak_result_2(0):= ((((block_permuted_tweak_2(0)>>0)&1.U)^((block_permuted_tweak_2(0)>>1)&1.U))<<3) | (((block_permuted_tweak_2(0)>>3)&1.U)<<2) | (((block_permuted_tweak_2(0)>>2)&1.U)<<1) | ((block_permuted_tweak_2(0)>>1)&1.U)
        block_tweak_result_2(1):= ((((block_permuted_tweak_2(1)>>0)&1.U)^((block_permuted_tweak_2(1)>>1)&1.U))<<3) | (((block_permuted_tweak_2(1)>>3)&1.U)<<2) | (((block_permuted_tweak_2(1)>>2)&1.U)<<1) | ((block_permuted_tweak_2(1)>>1)&1.U)
        block_tweak_result_2(2):= block_permuted_tweak_2(2)
        block_tweak_result_2(3):= ((((block_permuted_tweak_2(3)>>0)&1.U)^((block_permuted_tweak_2(3)>>1)&1.U))<<3) | (((block_permuted_tweak_2(3)>>3)&1.U)<<2) | (((block_permuted_tweak_2(3)>>2)&1.U)<<1) | ((block_permuted_tweak_2(3)>>1)&1.U)
        block_tweak_result_2(4):= ((((block_permuted_tweak_2(4)>>0)&1.U)^((block_permuted_tweak_2(4)>>1)&1.U))<<3) | (((block_permuted_tweak_2(4)>>3)&1.U)<<2) | (((block_permuted_tweak_2(4)>>2)&1.U)<<1) | ((block_permuted_tweak_2(4)>>1)&1.U)
        block_tweak_result_2(5):= block_permuted_tweak_2(5)
        block_tweak_result_2(6):= block_permuted_tweak_2(6)
        block_tweak_result_2(7):= block_permuted_tweak_2(7)
        block_tweak_result_2(8):= ((((block_permuted_tweak_2(8)>>0)&1.U)^((block_permuted_tweak_2(8)>>1)&1.U))<<3) | (((block_permuted_tweak_2(8)>>3)&1.U)<<2) | (((block_permuted_tweak_2(8)>>2)&1.U)<<1) | ((block_permuted_tweak_2(8)>>1)&1.U)
        block_tweak_result_2(9):= block_permuted_tweak_2(9)
        block_tweak_result_2(10):= block_permuted_tweak_2(10)
        block_tweak_result_2(11):= ((((block_permuted_tweak_2(11)>>0)&1.U)^((block_permuted_tweak_2(11)>>1)&1.U))<<3) | (((block_permuted_tweak_2(11)>>3)&1.U)<<2) | (((block_permuted_tweak_2(11)>>2)&1.U)<<1) | ((block_permuted_tweak_2(11)>>1)&1.U)
        block_tweak_result_2(12):= block_permuted_tweak_2(12)
        block_tweak_result_2(13):= ((((block_permuted_tweak_2(13)>>0)&1.U)^((block_permuted_tweak_2(13)>>1)&1.U))<<3) | (((block_permuted_tweak_2(13)>>3)&1.U)<<2) | (((block_permuted_tweak_2(13)>>2)&1.U)<<1) | ((block_permuted_tweak_2(13)>>1)&1.U)
        block_tweak_result_2(14):= block_permuted_tweak_2(14)
        block_tweak_result_2(15):= block_permuted_tweak_2(15)

        //printf(p"End of Loop 3\n")
        //printf(p"Block Permuted Tweak 2 : $block_permuted_tweak_2\n")
        //printf(p"Block Tweak Result 2: $block_tweak_result_2\n")

        //loop iteration 4
        block_permuted_tweak_3(0) := block_tweak_result_2(tweak_permutation(0))
        block_permuted_tweak_3(1) := block_tweak_result_2(tweak_permutation(1))
        block_permuted_tweak_3(2) := block_tweak_result_2(tweak_permutation(2))
        block_permuted_tweak_3(3) := block_tweak_result_2(tweak_permutation(3))
        block_permuted_tweak_3(4) := block_tweak_result_2(tweak_permutation(4))
        block_permuted_tweak_3(5) := block_tweak_result_2(tweak_permutation(5))
        block_permuted_tweak_3(6) := block_tweak_result_2(tweak_permutation(6))
        block_permuted_tweak_3(7) := block_tweak_result_2(tweak_permutation(7))
        block_permuted_tweak_3(8) := block_tweak_result_2(tweak_permutation(8))
        block_permuted_tweak_3(9) := block_tweak_result_2(tweak_permutation(9))
        block_permuted_tweak_3(10) := block_tweak_result_2(tweak_permutation(10))
        block_permuted_tweak_3(11) := block_tweak_result_2(tweak_permutation(11))
        block_permuted_tweak_3(12) := block_tweak_result_2(tweak_permutation(12))
        block_permuted_tweak_3(13) := block_tweak_result_2(tweak_permutation(13))
        block_permuted_tweak_3(14) := block_tweak_result_2(tweak_permutation(14))
        block_permuted_tweak_3(15) := block_tweak_result_2(tweak_permutation(15))
        
        block_tweak_result_3(0):= ((((block_permuted_tweak_3(0)>>0)&1.U)^((block_permuted_tweak_3(0)>>1)&1.U))<<3) | (((block_permuted_tweak_3(0)>>3)&1.U)<<2) | (((block_permuted_tweak_3(0)>>2)&1.U)<<1) | ((block_permuted_tweak_3(0)>>1)&1.U)
        block_tweak_result_3(1):= ((((block_permuted_tweak_3(1)>>0)&1.U)^((block_permuted_tweak_3(1)>>1)&1.U))<<3) | (((block_permuted_tweak_3(1)>>3)&1.U)<<2) | (((block_permuted_tweak_3(1)>>2)&1.U)<<1) | ((block_permuted_tweak_3(1)>>1)&1.U)
        block_tweak_result_3(2):= block_permuted_tweak_3(2)
        block_tweak_result_3(3):= ((((block_permuted_tweak_3(3)>>0)&1.U)^((block_permuted_tweak_3(3)>>1)&1.U))<<3) | (((block_permuted_tweak_3(3)>>3)&1.U)<<2) | (((block_permuted_tweak_3(3)>>2)&1.U)<<1) | ((block_permuted_tweak_3(3)>>1)&1.U)
        block_tweak_result_3(4):= ((((block_permuted_tweak_3(4)>>0)&1.U)^((block_permuted_tweak_3(4)>>1)&1.U))<<3) | (((block_permuted_tweak_3(4)>>3)&1.U)<<2) | (((block_permuted_tweak_3(4)>>2)&1.U)<<1) | ((block_permuted_tweak_3(4)>>1)&1.U)
        block_tweak_result_3(5):= block_permuted_tweak_3(5)
        block_tweak_result_3(6):= block_permuted_tweak_3(6)
        block_tweak_result_3(7):= block_permuted_tweak_3(7)
        block_tweak_result_3(8):= ((((block_permuted_tweak_3(8)>>0)&1.U)^((block_permuted_tweak_3(8)>>1)&1.U))<<3) | (((block_permuted_tweak_3(8)>>3)&1.U)<<2) | (((block_permuted_tweak_3(8)>>2)&1.U)<<1) | ((block_permuted_tweak_3(8)>>1)&1.U)
        block_tweak_result_3(9):= block_permuted_tweak_3(9)
        block_tweak_result_3(10):= block_permuted_tweak_3(10)
        block_tweak_result_3(11):= ((((block_permuted_tweak_3(11)>>0)&1.U)^((block_permuted_tweak_3(11)>>1)&1.U))<<3) | (((block_permuted_tweak_3(11)>>3)&1.U)<<2) | (((block_permuted_tweak_3(11)>>2)&1.U)<<1) | ((block_permuted_tweak_3(11)>>1)&1.U)
        block_tweak_result_3(12):= block_permuted_tweak_3(12)
        block_tweak_result_3(13):= ((((block_permuted_tweak_3(13)>>0)&1.U)^((block_permuted_tweak_3(13)>>1)&1.U))<<3) | (((block_permuted_tweak_3(13)>>3)&1.U)<<2) | (((block_permuted_tweak_3(13)>>2)&1.U)<<1) | ((block_permuted_tweak_3(13)>>1)&1.U)
        block_tweak_result_3(14):= block_permuted_tweak_3(14)
        block_tweak_result_3(15):= block_permuted_tweak_3(15)

        //printf(p"End of Loop 4\n")
        //printf(p"Block Permuted Tweak 3 : $block_permuted_tweak_3\n")
        //printf(p"Block Tweak Result 3: $block_tweak_result_3\n")

        //loop iteration 5

        block_permuted_tweak_4(0) := block_tweak_result_3(tweak_permutation(0))
        block_permuted_tweak_4(1) := block_tweak_result_3(tweak_permutation(1))
        block_permuted_tweak_4(2) := block_tweak_result_3(tweak_permutation(2))
        block_permuted_tweak_4(3) := block_tweak_result_3(tweak_permutation(3))
        block_permuted_tweak_4(4) := block_tweak_result_3(tweak_permutation(4))
        block_permuted_tweak_4(5) := block_tweak_result_3(tweak_permutation(5))
        block_permuted_tweak_4(6) := block_tweak_result_3(tweak_permutation(6))
        block_permuted_tweak_4(7) := block_tweak_result_3(tweak_permutation(7))
        block_permuted_tweak_4(8) := block_tweak_result_3(tweak_permutation(8))
        block_permuted_tweak_4(9) := block_tweak_result_3(tweak_permutation(9))
        block_permuted_tweak_4(10) := block_tweak_result_3(tweak_permutation(10))
        block_permuted_tweak_4(11) := block_tweak_result_3(tweak_permutation(11))
        block_permuted_tweak_4(12) := block_tweak_result_3(tweak_permutation(12))
        block_permuted_tweak_4(13) := block_tweak_result_3(tweak_permutation(13))
        block_permuted_tweak_4(14) := block_tweak_result_3(tweak_permutation(14))
        block_permuted_tweak_4(15) := block_tweak_result_3(tweak_permutation(15))
        
        block_tweak_result_4(0):= ((((block_permuted_tweak_4(0)>>0)&1.U)^((block_permuted_tweak_4(0)>>1)&1.U))<<3) | (((block_permuted_tweak_4(0)>>3)&1.U)<<2) | (((block_permuted_tweak_4(0)>>2)&1.U)<<1) | ((block_permuted_tweak_4(0)>>1)&1.U)
        block_tweak_result_4(1):= ((((block_permuted_tweak_4(1)>>0)&1.U)^((block_permuted_tweak_4(1)>>1)&1.U))<<3) | (((block_permuted_tweak_4(1)>>3)&1.U)<<2) | (((block_permuted_tweak_4(1)>>2)&1.U)<<1) | ((block_permuted_tweak_4(1)>>1)&1.U)
        block_tweak_result_4(2):= block_permuted_tweak_4(2)
        block_tweak_result_4(3):= ((((block_permuted_tweak_4(3)>>0)&1.U)^((block_permuted_tweak_4(3)>>1)&1.U))<<3) | (((block_permuted_tweak_4(3)>>3)&1.U)<<2) | (((block_permuted_tweak_4(3)>>2)&1.U)<<1) | ((block_permuted_tweak_4(3)>>1)&1.U)
        block_tweak_result_4(4):= ((((block_permuted_tweak_4(4)>>0)&1.U)^((block_permuted_tweak_4(4)>>1)&1.U))<<3) | (((block_permuted_tweak_4(4)>>3)&1.U)<<2) | (((block_permuted_tweak_4(4)>>2)&1.U)<<1) | ((block_permuted_tweak_4(4)>>1)&1.U)
        block_tweak_result_4(5):= block_permuted_tweak_4(5)
        block_tweak_result_4(6):= block_permuted_tweak_4(6)
        block_tweak_result_4(7):= block_permuted_tweak_4(7)
        block_tweak_result_4(8):= ((((block_permuted_tweak_4(8)>>0)&1.U)^((block_permuted_tweak_4(8)>>1)&1.U))<<3) | (((block_permuted_tweak_4(8)>>3)&1.U)<<2) | (((block_permuted_tweak_4(8)>>2)&1.U)<<1) | ((block_permuted_tweak_4(8)>>1)&1.U)
        block_tweak_result_4(9):= block_permuted_tweak_4(9)
        block_tweak_result_4(10):= block_permuted_tweak_4(10)
        block_tweak_result_4(11):= ((((block_permuted_tweak_4(11)>>0)&1.U)^((block_permuted_tweak_4(11)>>1)&1.U))<<3) | (((block_permuted_tweak_4(11)>>3)&1.U)<<2) | (((block_permuted_tweak_4(11)>>2)&1.U)<<1) | ((block_permuted_tweak_4(11)>>1)&1.U)
        block_tweak_result_4(12):= block_permuted_tweak_4(12)
        block_tweak_result_4(13):= ((((block_permuted_tweak_4(13)>>0)&1.U)^((block_permuted_tweak_4(13)>>1)&1.U))<<3) | (((block_permuted_tweak_4(13)>>3)&1.U)<<2) | (((block_permuted_tweak_4(13)>>2)&1.U)<<1) | ((block_permuted_tweak_4(13)>>1)&1.U)
        block_tweak_result_4(14):= block_permuted_tweak_4(14)
        block_tweak_result_4(15):= block_permuted_tweak_4(15)

        //printf(p"End of Loop 5\n")
        //printf(p"Block Permuted Tweak 4 : $block_permuted_tweak_4\n")
        //printf(p"Block Tweak Result 4: $block_tweak_result_4\n")

    
    //io.tweak_r := temp_tweak
    return_value:= (block_tweak_result_4(0) << (15-0)*4) + (block_tweak_result_4(1) << (15-1)*4) + (block_tweak_result_4(2) << (15-2)*4) + (block_tweak_result_4(3) << (15-3)*4) + (block_tweak_result_4(4) << (15-4)*4) + (block_tweak_result_4(5) << (15-5)*4) + (block_tweak_result_4(6) << (15-6)*4) + (block_tweak_result_4(7) << (15-7)*4) + (block_tweak_result_4(8) << (15-8)*4) + (block_tweak_result_4(9) << (15-9)*4) + (block_tweak_result_4(10) << (15-10)*4) + (block_tweak_result_4(11) << (15-11)*4) + (block_tweak_result_4(12) << (15-12)*4) + (block_tweak_result_4(13) << (15-13)*4) + (block_tweak_result_4(14) << (15-14)*4) + (block_tweak_result_4(15) << (15-15)*4)

    io.tweak_r := return_value
}

//// This is where the real function is 

class CalcRoundTweakey() extends Module {
val io = IO(new Bundle {
    //Inputs
    val tweak = Input(UInt(64.W)) 
    val k0 = Input(UInt(64.W))
    val backwards = Input(Bool())
    //Outputs
    val output = Output(UInt(64.W)) 

  }) //input bundle ends here

    val alpha_block = RegInit(VecInit(12.U(64.W), 0.U(64.W), 10.U(64.W), 12.U(64.W), 2.U(64.W), 9.U(64.W), 11.U(64.W), 7.U(64.W), 12.U(64.W), 9.U(64.W), 7.U(64.W), 12.U(64.W), 5.U(64.W), 0.U(64.W), 13.U(64.W), 13.U(64.W)))
    val round_constants_5 = RegInit(VecInit(11.U(64.W), 14.U(64.W), 5.U(64.W), 4.U(64.W), 6.U(64.W), 6.U(64.W), 12.U(64.W), 15.U(64.W), 3.U(64.W), 4.U(64.W), 14.U(64.W), 9.U(64.W), 0.U(64.W), 12.U(64.W), 6.U(64.W), 12.U(64.W)))

    val calctweakblock = Module(new CalcTweak())
    val block_tweak = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_k0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_calctweak_output = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_xor_1= WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_xor_2= WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_xor_3= WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

    calctweakblock.io.tweak := io.tweak
    //printf(p"Internal block io.tweak : ${calctweakblock.io.tweak}\n")
    //printf(p"Internal block io.tweak_r : ${calctweakblock.io.tweak_r}\n")

    
    //so far so good
    for (j <- 0 to 15) //hex to block
    {
        block_tweak(15-j) := ((io.tweak & ("hF".U << j*4)) >> j*4)
    }

    for (j <- 0 to 15) //hex to block
    {
        block_k0(15-j) := ((io.k0 & ("hF".U << j*4)) >> j*4)
    }

    for (j <- 0 to 15) //hex to block
    {
        block_calctweak_output(15-j) := ((calctweakblock.io.tweak_r & ("hF".U << j*4)) >> j*4)
    }

    //printf(p"block_tweak : $block_tweak \n block_k0 : $block_k0\n")
    //printf(p"block_calctweak_output : $block_calctweak_output \n")

    //fine so far
    
    //xor blocks output of CalcTweak and block k0

    block_xor_1(0) := block_calctweak_output(0) ^ block_k0(0)
    block_xor_1(1) := block_calctweak_output(1) ^ block_k0(1)
    block_xor_1(2) := block_calctweak_output(2) ^ block_k0(2)
    block_xor_1(3) := block_calctweak_output(3) ^ block_k0(3)
    block_xor_1(4) := block_calctweak_output(4) ^ block_k0(4)
    block_xor_1(5) := block_calctweak_output(5) ^ block_k0(5)
    block_xor_1(6) := block_calctweak_output(6) ^ block_k0(6)
    block_xor_1(7) := block_calctweak_output(7) ^ block_k0(7)
    block_xor_1(8) := block_calctweak_output(8) ^ block_k0(8)
    block_xor_1(9) := block_calctweak_output(9) ^ block_k0(9)
    block_xor_1(10) := block_calctweak_output(10) ^ block_k0(10)
    block_xor_1(11) := block_calctweak_output(11) ^ block_k0(11)
    block_xor_1(12) := block_calctweak_output(12) ^ block_k0(12)
    block_xor_1(13) := block_calctweak_output(13) ^ block_k0(13)
    block_xor_1(14) := block_calctweak_output(14) ^ block_k0(14)
    block_xor_1(15) := block_calctweak_output(15) ^ block_k0(15)

    //printf(p"block_xor_1: $block_xor_1\n")
    //rounds is 5, so pick the correct constant

    block_xor_2(0) := block_xor_1(0) ^ round_constants_5(0)
    block_xor_2(1) := block_xor_1(1) ^ round_constants_5(1)
    block_xor_2(2) := block_xor_1(2) ^ round_constants_5(2)
    block_xor_2(3) := block_xor_1(3) ^ round_constants_5(3)
    block_xor_2(4) := block_xor_1(4) ^ round_constants_5(4)
    block_xor_2(5) := block_xor_1(5) ^ round_constants_5(5)
    block_xor_2(6) := block_xor_1(6) ^ round_constants_5(6)
    block_xor_2(7) := block_xor_1(7) ^ round_constants_5(7)
    block_xor_2(8) := block_xor_1(8) ^ round_constants_5(8)
    block_xor_2(9) := block_xor_1(9) ^ round_constants_5(9)
    block_xor_2(10) := block_xor_1(10) ^ round_constants_5(10)
    block_xor_2(11) := block_xor_1(11) ^ round_constants_5(11)
    block_xor_2(12) := block_xor_1(12) ^ round_constants_5(12)
    block_xor_2(13) := block_xor_1(13) ^ round_constants_5(13)
    block_xor_2(14) := block_xor_1(14) ^ round_constants_5(14)
    block_xor_2(15) := block_xor_1(15) ^ round_constants_5(15)
    //printf(p"block_xor_2: $block_xor_2\n")

    when(io.backwards)
    {
        block_xor_3(0) := block_xor_2(0) ^ alpha_block(0)
        block_xor_3(1) := block_xor_2(1) ^ alpha_block(1)
        block_xor_3(2) := block_xor_2(2) ^ alpha_block(2)
        block_xor_3(3) := block_xor_2(3) ^ alpha_block(3)
        block_xor_3(4) := block_xor_2(4) ^ alpha_block(4)
        block_xor_3(5) := block_xor_2(5) ^ alpha_block(5)
        block_xor_3(6) := block_xor_2(6) ^ alpha_block(6)
        block_xor_3(7) := block_xor_2(7) ^ alpha_block(7)
        block_xor_3(8) := block_xor_2(8) ^ alpha_block(8)
        block_xor_3(9) := block_xor_2(9) ^ alpha_block(9)
        block_xor_3(10) := block_xor_2(10) ^ alpha_block(10)
        block_xor_3(11) := block_xor_2(11) ^ alpha_block(11)
        block_xor_3(12) := block_xor_2(12) ^ alpha_block(12)
        block_xor_3(13) := block_xor_2(13) ^ alpha_block(13)
        block_xor_3(14) := block_xor_2(14) ^ alpha_block(14)
        block_xor_3(15) := block_xor_2(15) ^ alpha_block(15)

    }
    .otherwise
    {
        block_xor_3 := block_xor_2

    }

    //printf(p"block_xor_3: $block_xor_3\n")
    io.output := (block_xor_3(0) << (15-0)*4) + (block_xor_3(1) << (15-1)*4) + (block_xor_3(2) << (15-2)*4) + (block_xor_3(3) << (15-3)*4) + (block_xor_3(4) << (15-4)*4) + (block_xor_3(5) << (15-5)*4) + (block_xor_3(6) << (15-6)*4) + (block_xor_3(7) << (15-7)*4) + (block_xor_3(8) << (15-8)*4) + (block_xor_3(9) << (15-9)*4) + (block_xor_3(10) << (15-10)*4) + (block_xor_3(11) << (15-11)*4) + (block_xor_3(12) << (15-12)*4) + (block_xor_3(13) << (15-13)*4) + (block_xor_3(14) << (15-14)*4) + (block_xor_3(15) << (15-15)*4)
            
    
}