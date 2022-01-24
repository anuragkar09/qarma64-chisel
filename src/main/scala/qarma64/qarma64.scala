import chisel3._
import chisel3.util._

class CalcTweak_5rounds() extends Module {
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


class CalcTweak_4rounds() extends Module {
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

    val block_permuted_tweak_0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_2 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_3 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

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

    
    //io.tweak_r := temp_tweak
    return_value:= (block_tweak_result_3(0) << (15-0)*4) + (block_tweak_result_3(1) << (15-1)*4) + (block_tweak_result_3(2) << (15-2)*4) + (block_tweak_result_3(3) << (15-3)*4) + (block_tweak_result_3(4) << (15-4)*4) + (block_tweak_result_3(5) << (15-5)*4) + (block_tweak_result_3(6) << (15-6)*4) + (block_tweak_result_3(7) << (15-7)*4) + (block_tweak_result_3(8) << (15-8)*4) + (block_tweak_result_3(9) << (15-9)*4) + (block_tweak_result_3(10) << (15-10)*4) + (block_tweak_result_3(11) << (15-11)*4) + (block_tweak_result_3(12) << (15-12)*4) + (block_tweak_result_3(13) << (15-13)*4) + (block_tweak_result_3(14) << (15-14)*4) + (block_tweak_result_3(15) << (15-15)*4)

    io.tweak_r := return_value
}

class CalcTweak_3rounds() extends Module {
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

    val block_permuted_tweak_0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_2 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

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
    
    //io.tweak_r := temp_tweak
    return_value:= (block_tweak_result_2(0) << (15-0)*4) + (block_tweak_result_2(1) << (15-1)*4) + (block_tweak_result_2(2) << (15-2)*4) + (block_tweak_result_2(3) << (15-3)*4) + (block_tweak_result_2(4) << (15-4)*4) + (block_tweak_result_2(5) << (15-5)*4) + (block_tweak_result_2(6) << (15-6)*4) + (block_tweak_result_2(7) << (15-7)*4) + (block_tweak_result_2(8) << (15-8)*4) + (block_tweak_result_2(9) << (15-9)*4) + (block_tweak_result_2(10) << (15-10)*4) + (block_tweak_result_2(11) << (15-11)*4) + (block_tweak_result_2(12) << (15-12)*4) + (block_tweak_result_2(13) << (15-13)*4) + (block_tweak_result_2(14) << (15-14)*4) + (block_tweak_result_2(15) << (15-15)*4)

    io.tweak_r := return_value
}

class CalcTweak_2rounds() extends Module {
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
  

    val block_permuted_tweak_0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
  

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
        
    
    //io.tweak_r := temp_tweak
    return_value:= (block_tweak_result_1(0) << (15-0)*4) + (block_tweak_result_1(1) << (15-1)*4) + (block_tweak_result_1(2) << (15-2)*4) + (block_tweak_result_1(3) << (15-3)*4) + (block_tweak_result_1(4) << (15-4)*4) + (block_tweak_result_1(5) << (15-5)*4) + (block_tweak_result_1(6) << (15-6)*4) + (block_tweak_result_1(7) << (15-7)*4) + (block_tweak_result_1(8) << (15-8)*4) + (block_tweak_result_1(9) << (15-9)*4) + (block_tweak_result_1(10) << (15-10)*4) + (block_tweak_result_1(11) << (15-11)*4) + (block_tweak_result_1(12) << (15-12)*4) + (block_tweak_result_1(13) << (15-13)*4) + (block_tweak_result_1(14) << (15-14)*4) + (block_tweak_result_1(15) << (15-15)*4)

    io.tweak_r := return_value
}


class CalcTweak_1rounds() extends Module {
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
    
    val block_permuted_tweak_0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    

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
       
        
    
    //io.tweak_r := temp_tweak
    return_value:= (block_tweak_result_0(0) << (15-0)*4) + (block_tweak_result_0(1) << (15-1)*4) + (block_tweak_result_0(2) << (15-2)*4) + (block_tweak_result_0(3) << (15-3)*4) + (block_tweak_result_0(4) << (15-4)*4) + (block_tweak_result_0(5) << (15-5)*4) + (block_tweak_result_0(6) << (15-6)*4) + (block_tweak_result_0(7) << (15-7)*4) + (block_tweak_result_0(8) << (15-8)*4) + (block_tweak_result_0(9) << (15-9)*4) + (block_tweak_result_0(10) << (15-10)*4) + (block_tweak_result_0(11) << (15-11)*4) + (block_tweak_result_0(12) << (15-12)*4) + (block_tweak_result_0(13) << (15-13)*4) + (block_tweak_result_0(14) << (15-14)*4) + (block_tweak_result_0(15) << (15-15)*4)

    io.tweak_r := return_value
}

class CalcTweak_0rounds() extends Module {
val io = IO(new Bundle {
    //Inputs
    val tweak = Input(UInt(64.W)) 
    //Outputs
    val tweak_r = Output(UInt(64.W)) 

  }) //input bundle ends here
    //experimental code
    io.tweak_r := io.tweak
}

class CalcRoundTweakey_5rounds() extends Module {
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

    val calctweakblock = Module(new CalcTweak_5rounds())
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

class CalcRoundTweakey_4rounds() extends Module {
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

    val calctweakblock = Module(new CalcTweak_4rounds())
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

class CalcRoundTweakey_3rounds() extends Module {
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

    val calctweakblock = Module(new CalcTweak_3rounds())
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

class CalcRoundTweakey_2rounds() extends Module {
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

    val calctweakblock = Module(new CalcTweak_2rounds())
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

class CalcRoundTweakey_1rounds() extends Module {
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

    val calctweakblock = Module(new CalcTweak_1rounds())
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

class CalcRoundTweakey_0rounds() extends Module {
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

    val calctweakblock = Module(new CalcTweak_0rounds())
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

class Round() extends Module{

val io = IO(new Bundle {
    //Inputs
    val state = Input(UInt(64.W)) 
    val tweakey = Input(UInt(64.W)) 
    val backwards = Input(Bool())
    //rounds are fixed at 5
    //Outputs
    val round_state = Output(UInt(64.W)) 

  }) //input bundle ends here
    val state_permutation = RegInit(VecInit(0.U(64.W), 11.U(64.W), 6.U(64.W), 13.U(64.W), 10.U(64.W), 1.U(64.W), 12.U(64.W), 7.U(64.W), 5.U(64.W), 14.U(64.W), 3.U(64.W), 8.U(64.W), 15.U(64.W), 4.U(64.W), 9.U(64.W), 2.U(64.W)))
    val state_permutation_inv = RegInit(VecInit(0.U(64.W), 5.U(64.W), 15.U(64.W), 10.U(64.W), 13.U(64.W), 8.U(64.W), 2.U(64.W), 7.U(64.W), 11.U(64.W), 14.U(64.W), 4.U(64.W), 1.U(64.W), 6.U(64.W), 3.U(64.W), 9.U(64.W), 12.U(64.W)))
    val used_sbox = RegInit(VecInit(0.U(64.W), 14.U(64.W), 2.U(64.W), 10.U(64.W), 9.U(64.W), 15.U(64.W), 8.U(64.W), 11.U(64.W), 6.U(64.W), 4.U(64.W), 3.U(64.W), 7.U(64.W), 13.U(64.W), 12.U(64.W), 1.U(64.W), 5.U(64.W)))
    val used_sbox_inv = used_sbox
    val block_state = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweakey = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val xor_block_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val xor_block_2 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val permuted_state_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val mixcolumns_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val subbytes_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val return_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))


    val incol_0_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_0_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_0_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_0_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    
    val incol_1_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_1_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_1_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_1_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))

    val outcol_0_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_0_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_0_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_0_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))

    val outcol_1_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_1_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_1_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_1_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))

    for (j <- 0 to 15) //hex to block
    {
        block_state(15-j) := ((io.state & ("hF".U << j*4)) >> j*4)
        block_tweakey(15-j) := ((io.tweakey & ("hF".U << j*4)) >> j*4)
    }

    when(!io.backwards)
    {
        xor_block_1(0) := block_state(0) ^ block_tweakey(0)
        xor_block_1(1) := block_state(1) ^ block_tweakey(1)
        xor_block_1(2) := block_state(2) ^ block_tweakey(2)
        xor_block_1(3) := block_state(3) ^ block_tweakey(3)
        xor_block_1(4) := block_state(4) ^ block_tweakey(4)
        xor_block_1(5) := block_state(5) ^ block_tweakey(5)
        xor_block_1(6) := block_state(6) ^ block_tweakey(6)
        xor_block_1(7) := block_state(7) ^ block_tweakey(7)
        xor_block_1(8) := block_state(8) ^ block_tweakey(8)
        xor_block_1(9) := block_state(9) ^ block_tweakey(9)
        xor_block_1(10) := block_state(10) ^ block_tweakey(10)
        xor_block_1(11) := block_state(11) ^ block_tweakey(11)
        xor_block_1(12) := block_state(12) ^ block_tweakey(12)
        xor_block_1(13) := block_state(13) ^ block_tweakey(13)
        xor_block_1(14) := block_state(14) ^ block_tweakey(14)
        xor_block_1(15) := block_state(15) ^ block_tweakey(15)

        permuted_state_block(0) := xor_block_1(state_permutation(0))
        permuted_state_block(1) := xor_block_1(state_permutation(1))
        permuted_state_block(2) := xor_block_1(state_permutation(2))
        permuted_state_block(3) := xor_block_1(state_permutation(3))
        permuted_state_block(4) := xor_block_1(state_permutation(4))
        permuted_state_block(5) := xor_block_1(state_permutation(5))
        permuted_state_block(6) := xor_block_1(state_permutation(6))
        permuted_state_block(7) := xor_block_1(state_permutation(7))
        permuted_state_block(8) := xor_block_1(state_permutation(8))
        permuted_state_block(9) := xor_block_1(state_permutation(9))
        permuted_state_block(10) := xor_block_1(state_permutation(10))
        permuted_state_block(11) := xor_block_1(state_permutation(11))
        permuted_state_block(12) := xor_block_1(state_permutation(12))
        permuted_state_block(13) := xor_block_1(state_permutation(13))
        permuted_state_block(14) := xor_block_1(state_permutation(14))
        permuted_state_block(15) := xor_block_1(state_permutation(15))

        incol_0_0(0) := permuted_state_block(0)
        incol_0_0(1) := permuted_state_block(4)
        incol_0_0(2) := permuted_state_block(8)
        incol_0_0(3) := permuted_state_block(12)

        incol_0_1(0) := permuted_state_block(0+1)
        incol_0_1(1) := permuted_state_block(4+1)
        incol_0_1(2) := permuted_state_block(8+1)
        incol_0_1(3) := permuted_state_block(12+1)
        
        incol_0_2(0) := permuted_state_block(0+2)
        incol_0_2(1) := permuted_state_block(4+2)
        incol_0_2(2) := permuted_state_block(8+2)
        incol_0_2(3) := permuted_state_block(12+2)

        incol_0_3(0) := permuted_state_block(0+3)
        incol_0_3(1) := permuted_state_block(4+3)
        incol_0_3(2) := permuted_state_block(8+3)
        incol_0_3(3) := permuted_state_block(12+3)

        outcol_0_0(0) := (((incol_0_0(1) << 1) | (incol_0_0(1) >> (4-1))) % 16.U) ^ (((incol_0_0(2) << 2) | (incol_0_0(2) >> (4-2))) % 16.U) ^ (((incol_0_0(3) << 1) | (incol_0_0(3) >> (4-1))) % 16.U)
        outcol_0_0(1) := (((incol_0_0(0) << 1) | (incol_0_0(0) >> (4-1))) % 16.U) ^ (((incol_0_0(2) << 1) | (incol_0_0(2) >> (4-1))) % 16.U) ^ (((incol_0_0(3) << 2) | (incol_0_0(3) >> (4-2))) % 16.U)
        outcol_0_0(2) := (((incol_0_0(0) << 2) | (incol_0_0(0) >> (4-2))) % 16.U) ^ (((incol_0_0(1) << 1) | (incol_0_0(1) >> (4-1))) % 16.U) ^ (((incol_0_0(3) << 1) | (incol_0_0(3) >> (4-1))) % 16.U)
        outcol_0_0(3) := (((incol_0_0(0) << 1) | (incol_0_0(0) >> (4-1))) % 16.U) ^ (((incol_0_0(1) << 2) | (incol_0_0(1) >> (4-2))) % 16.U) ^ (((incol_0_0(2) << 1) | (incol_0_0(2) >> (4-1))) % 16.U)

        outcol_0_1(0) := (((incol_0_1(1) << 1) | (incol_0_1(1) >> (4-1))) % 16.U) ^ (((incol_0_1(2) << 2) | (incol_0_1(2) >> (4-2))) % 16.U) ^ (((incol_0_1(3) << 1) | (incol_0_1(3) >> (4-1))) % 16.U)
        outcol_0_1(1) := (((incol_0_1(0) << 1) | (incol_0_1(0) >> (4-1))) % 16.U) ^ (((incol_0_1(2) << 1) | (incol_0_1(2) >> (4-1))) % 16.U) ^ (((incol_0_1(3) << 2) | (incol_0_1(3) >> (4-2))) % 16.U)
        outcol_0_1(2) := (((incol_0_1(0) << 2) | (incol_0_1(0) >> (4-2))) % 16.U) ^ (((incol_0_1(1) << 1) | (incol_0_1(1) >> (4-1))) % 16.U) ^ (((incol_0_1(3) << 1) | (incol_0_1(3) >> (4-1))) % 16.U)
        outcol_0_1(3) := (((incol_0_1(0) << 1) | (incol_0_1(0) >> (4-1))) % 16.U) ^ (((incol_0_1(1) << 2) | (incol_0_1(1) >> (4-2))) % 16.U) ^ (((incol_0_1(2) << 1) | (incol_0_1(2) >> (4-1))) % 16.U)

        outcol_0_2(0) := (((incol_0_2(1) << 1) | (incol_0_2(1) >> (4-1))) % 16.U) ^ (((incol_0_2(2) << 2) | (incol_0_2(2) >> (4-2))) % 16.U) ^ (((incol_0_2(3) << 1) | (incol_0_2(3) >> (4-1))) % 16.U)
        outcol_0_2(1) := (((incol_0_2(0) << 1) | (incol_0_2(0) >> (4-1))) % 16.U) ^ (((incol_0_2(2) << 1) | (incol_0_2(2) >> (4-1))) % 16.U) ^ (((incol_0_2(3) << 2) | (incol_0_2(3) >> (4-2))) % 16.U)
        outcol_0_2(2) := (((incol_0_2(0) << 2) | (incol_0_2(0) >> (4-2))) % 16.U) ^ (((incol_0_2(1) << 1) | (incol_0_2(1) >> (4-1))) % 16.U) ^ (((incol_0_2(3) << 1) | (incol_0_2(3) >> (4-1))) % 16.U)
        outcol_0_2(3) := (((incol_0_2(0) << 1) | (incol_0_2(0) >> (4-1))) % 16.U) ^ (((incol_0_2(1) << 2) | (incol_0_2(1) >> (4-2))) % 16.U) ^ (((incol_0_2(2) << 1) | (incol_0_2(2) >> (4-1))) % 16.U)

        outcol_0_3(0) := (((incol_0_3(1) << 1) | (incol_0_3(1) >> (4-1))) % 16.U) ^ (((incol_0_3(2) << 2) | (incol_0_3(2) >> (4-2))) % 16.U) ^ (((incol_0_3(3) << 1) | (incol_0_3(3) >> (4-1))) % 16.U)
        outcol_0_3(1) := (((incol_0_3(0) << 1) | (incol_0_3(0) >> (4-1))) % 16.U) ^ (((incol_0_3(2) << 1) | (incol_0_3(2) >> (4-1))) % 16.U) ^ (((incol_0_3(3) << 2) | (incol_0_3(3) >> (4-2))) % 16.U)
        outcol_0_3(2) := (((incol_0_3(0) << 2) | (incol_0_3(0) >> (4-2))) % 16.U) ^ (((incol_0_3(1) << 1) | (incol_0_3(1) >> (4-1))) % 16.U) ^ (((incol_0_3(3) << 1) | (incol_0_3(3) >> (4-1))) % 16.U)
        outcol_0_3(3) := (((incol_0_3(0) << 1) | (incol_0_3(0) >> (4-1))) % 16.U) ^ (((incol_0_3(1) << 2) | (incol_0_3(1) >> (4-2))) % 16.U) ^ (((incol_0_3(2) << 1) | (incol_0_3(2) >> (4-1))) % 16.U)
       
        mixcolumns_block(0) := outcol_0_0(0)
        mixcolumns_block(1) := outcol_0_1(0)
        mixcolumns_block(2) := outcol_0_2(0)
        mixcolumns_block(3) := outcol_0_3(0)
        mixcolumns_block(4) := outcol_0_0(1)
        mixcolumns_block(5) := outcol_0_1(1)
        mixcolumns_block(6) := outcol_0_2(1)
        mixcolumns_block(7) := outcol_0_3(1)
        mixcolumns_block(8) := outcol_0_0(2)
        mixcolumns_block(9) := outcol_0_1(2)
        mixcolumns_block(10) := outcol_0_2(2)
        mixcolumns_block(11) := outcol_0_3(2)
        mixcolumns_block(12) := outcol_0_0(3)
        mixcolumns_block(13) := outcol_0_1(3)
        mixcolumns_block(14) := outcol_0_2(3)
        mixcolumns_block(15) := outcol_0_3(3)

        subbytes_block(0) := used_sbox(mixcolumns_block(0))
        subbytes_block(1) := used_sbox(mixcolumns_block(1))
        subbytes_block(2) := used_sbox(mixcolumns_block(2))
        subbytes_block(3) := used_sbox(mixcolumns_block(3))
        subbytes_block(4) := used_sbox(mixcolumns_block(4))
        subbytes_block(5) := used_sbox(mixcolumns_block(5))
        subbytes_block(6) := used_sbox(mixcolumns_block(6))
        subbytes_block(7) := used_sbox(mixcolumns_block(7))
        subbytes_block(8) := used_sbox(mixcolumns_block(8))
        subbytes_block(9) := used_sbox(mixcolumns_block(9))
        subbytes_block(10) := used_sbox(mixcolumns_block(10))
        subbytes_block(11) := used_sbox(mixcolumns_block(11))
        subbytes_block(12) := used_sbox(mixcolumns_block(12))
        subbytes_block(13) := used_sbox(mixcolumns_block(13))
        subbytes_block(14) := used_sbox(mixcolumns_block(14))
        subbytes_block(15) := used_sbox(mixcolumns_block(15))

        return_block := subbytes_block

    }
    .otherwise
    {
        subbytes_block(0) := used_sbox_inv(block_state(0))
        subbytes_block(1) := used_sbox_inv(block_state(1))
        subbytes_block(2) := used_sbox_inv(block_state(2))
        subbytes_block(3) := used_sbox_inv(block_state(3))
        subbytes_block(4) := used_sbox_inv(block_state(4))
        subbytes_block(5) := used_sbox_inv(block_state(5))
        subbytes_block(6) := used_sbox_inv(block_state(6))
        subbytes_block(7) := used_sbox_inv(block_state(7))
        subbytes_block(8) := used_sbox_inv(block_state(8))
        subbytes_block(9) := used_sbox_inv(block_state(9))
        subbytes_block(10) := used_sbox_inv(block_state(10))
        subbytes_block(11) := used_sbox_inv(block_state(11))
        subbytes_block(12) := used_sbox_inv(block_state(12))
        subbytes_block(13) := used_sbox_inv(block_state(13))
        subbytes_block(14) := used_sbox_inv(block_state(14))
        subbytes_block(15) := used_sbox_inv(block_state(15))

        //printf(p"subbytes_block : $subbytes_block\n")

        incol_1_0(0) := subbytes_block(0)
        incol_1_0(1) := subbytes_block(4)
        incol_1_0(2) := subbytes_block(8)
        incol_1_0(3) := subbytes_block(12)

        incol_1_1(0) := subbytes_block(0+1)
        incol_1_1(1) := subbytes_block(4+1)
        incol_1_1(2) := subbytes_block(8+1)
        incol_1_1(3) := subbytes_block(12+1)
        
        incol_1_2(0) := subbytes_block(0+2)
        incol_1_2(1) := subbytes_block(4+2)
        incol_1_2(2) := subbytes_block(8+2)
        incol_1_2(3) := subbytes_block(12+2)

        incol_1_3(0) := subbytes_block(0+3)
        incol_1_3(1) := subbytes_block(4+3)
        incol_1_3(2) := subbytes_block(8+3)
        incol_1_3(3) := subbytes_block(12+3)

        //printf(p"incol_1_0 : $incol_1_0\n")
        //printf(p"incol_1_1 : $incol_1_1\n")
        //printf(p"incol_1_2 : $incol_1_2\n")
        //printf(p"incol_1_3 : $incol_1_3\n")

        outcol_1_0(0) := (((incol_1_0(1) << 1) | (incol_1_0(1) >> (4-1))) % 16.U) ^ (((incol_1_0(2) << 2) | (incol_1_0(2) >> (4-2))) % 16.U) ^ (((incol_1_0(3) << 1) | (incol_1_0(3) >> (4-1))) % 16.U)
        outcol_1_0(1) := (((incol_1_0(0) << 1) | (incol_1_0(0) >> (4-1))) % 16.U) ^ (((incol_1_0(2) << 1) | (incol_1_0(2) >> (4-1))) % 16.U) ^ (((incol_1_0(3) << 2) | (incol_1_0(3) >> (4-2))) % 16.U)
        outcol_1_0(2) := (((incol_1_0(0) << 2) | (incol_1_0(0) >> (4-2))) % 16.U) ^ (((incol_1_0(1) << 1) | (incol_1_0(1) >> (4-1))) % 16.U) ^ (((incol_1_0(3) << 1) | (incol_1_0(3) >> (4-1))) % 16.U)
        outcol_1_0(3) := (((incol_1_0(0) << 1) | (incol_1_0(0) >> (4-1))) % 16.U) ^ (((incol_1_0(1) << 2) | (incol_1_0(1) >> (4-2))) % 16.U) ^ (((incol_1_0(2) << 1) | (incol_1_0(2) >> (4-1))) % 16.U)

        outcol_1_1(0) := (((incol_1_1(1) << 1) | (incol_1_1(1) >> (4-1))) % 16.U) ^ (((incol_1_1(2) << 2) | (incol_1_1(2) >> (4-2))) % 16.U) ^ (((incol_1_1(3) << 1) | (incol_1_1(3) >> (4-1))) % 16.U)
        outcol_1_1(1) := (((incol_1_1(0) << 1) | (incol_1_1(0) >> (4-1))) % 16.U) ^ (((incol_1_1(2) << 1) | (incol_1_1(2) >> (4-1))) % 16.U) ^ (((incol_1_1(3) << 2) | (incol_1_1(3) >> (4-2))) % 16.U)
        outcol_1_1(2) := (((incol_1_1(0) << 2) | (incol_1_1(0) >> (4-2))) % 16.U) ^ (((incol_1_1(1) << 1) | (incol_1_1(1) >> (4-1))) % 16.U) ^ (((incol_1_1(3) << 1) | (incol_1_1(3) >> (4-1))) % 16.U)
        outcol_1_1(3) := (((incol_1_1(0) << 1) | (incol_1_1(0) >> (4-1))) % 16.U) ^ (((incol_1_1(1) << 2) | (incol_1_1(1) >> (4-2))) % 16.U) ^ (((incol_1_1(2) << 1) | (incol_1_1(2) >> (4-1))) % 16.U)

        outcol_1_2(0) := (((incol_1_2(1) << 1) | (incol_1_2(1) >> (4-1))) % 16.U) ^ (((incol_1_2(2) << 2) | (incol_1_2(2) >> (4-2))) % 16.U) ^ (((incol_1_2(3) << 1) | (incol_1_2(3) >> (4-1))) % 16.U)
        outcol_1_2(1) := (((incol_1_2(0) << 1) | (incol_1_2(0) >> (4-1))) % 16.U) ^ (((incol_1_2(2) << 1) | (incol_1_2(2) >> (4-1))) % 16.U) ^ (((incol_1_2(3) << 2) | (incol_1_2(3) >> (4-2))) % 16.U)
        outcol_1_2(2) := (((incol_1_2(0) << 2) | (incol_1_2(0) >> (4-2))) % 16.U) ^ (((incol_1_2(1) << 1) | (incol_1_2(1) >> (4-1))) % 16.U) ^ (((incol_1_2(3) << 1) | (incol_1_2(3) >> (4-1))) % 16.U)
        outcol_1_2(3) := (((incol_1_2(0) << 1) | (incol_1_2(0) >> (4-1))) % 16.U) ^ (((incol_1_2(1) << 2) | (incol_1_2(1) >> (4-2))) % 16.U) ^ (((incol_1_2(2) << 1) | (incol_1_2(2) >> (4-1))) % 16.U)

        outcol_1_3(0) := (((incol_1_3(1) << 1) | (incol_1_3(1) >> (4-1))) % 16.U) ^ (((incol_1_3(2) << 2) | (incol_1_3(2) >> (4-2))) % 16.U) ^ (((incol_1_3(3) << 1) | (incol_1_3(3) >> (4-1))) % 16.U)
        outcol_1_3(1) := (((incol_1_3(0) << 1) | (incol_1_3(0) >> (4-1))) % 16.U) ^ (((incol_1_3(2) << 1) | (incol_1_3(2) >> (4-1))) % 16.U) ^ (((incol_1_3(3) << 2) | (incol_1_3(3) >> (4-2))) % 16.U)
        outcol_1_3(2) := (((incol_1_3(0) << 2) | (incol_1_3(0) >> (4-2))) % 16.U) ^ (((incol_1_3(1) << 1) | (incol_1_3(1) >> (4-1))) % 16.U) ^ (((incol_1_3(3) << 1) | (incol_1_3(3) >> (4-1))) % 16.U)
        outcol_1_3(3) := (((incol_1_3(0) << 1) | (incol_1_3(0) >> (4-1))) % 16.U) ^ (((incol_1_3(1) << 2) | (incol_1_3(1) >> (4-2))) % 16.U) ^ (((incol_1_3(2) << 1) | (incol_1_3(2) >> (4-1))) % 16.U)


        //printf(p"outcol_1_0 : $outcol_1_0\n")
        //printf(p"outcol_1_1 : $outcol_1_1\n")
        //printf(p"outcol_1_2 : $outcol_1_2\n")
        //printf(p"outcol_1_3 : $outcol_1_3\n")

        mixcolumns_block(0) := (((incol_1_0(1) << 1) | (incol_1_0(1) >> (4-1))) % 16.U) ^ (((incol_1_0(2) << 2) | (incol_1_0(2) >> (4-2))) % 16.U) ^ (((incol_1_0(3) << 1) | (incol_1_0(3) >> (4-1))) % 16.U)
        mixcolumns_block(1) := (((incol_1_1(1) << 1) | (incol_1_1(1) >> (4-1))) % 16.U) ^ (((incol_1_1(2) << 2) | (incol_1_1(2) >> (4-2))) % 16.U) ^ (((incol_1_1(3) << 1) | (incol_1_1(3) >> (4-1))) % 16.U)
        mixcolumns_block(2) := (((incol_1_2(1) << 1) | (incol_1_2(1) >> (4-1))) % 16.U) ^ (((incol_1_2(2) << 2) | (incol_1_2(2) >> (4-2))) % 16.U) ^ (((incol_1_2(3) << 1) | (incol_1_2(3) >> (4-1))) % 16.U)
        mixcolumns_block(3) := (((incol_1_3(1) << 1) | (incol_1_3(1) >> (4-1))) % 16.U) ^ (((incol_1_3(2) << 2) | (incol_1_3(2) >> (4-2))) % 16.U) ^ (((incol_1_3(3) << 1) | (incol_1_3(3) >> (4-1))) % 16.U)
        mixcolumns_block(4) := (((incol_1_0(0) << 1) | (incol_1_0(0) >> (4-1))) % 16.U) ^ (((incol_1_0(2) << 1) | (incol_1_0(2) >> (4-1))) % 16.U) ^ (((incol_1_0(3) << 2) | (incol_1_0(3) >> (4-2))) % 16.U)
        mixcolumns_block(5) := (((incol_1_1(0) << 1) | (incol_1_1(0) >> (4-1))) % 16.U) ^ (((incol_1_1(2) << 1) | (incol_1_1(2) >> (4-1))) % 16.U) ^ (((incol_1_1(3) << 2) | (incol_1_1(3) >> (4-2))) % 16.U)
        mixcolumns_block(6) := (((incol_1_2(0) << 1) | (incol_1_2(0) >> (4-1))) % 16.U) ^ (((incol_1_2(2) << 1) | (incol_1_2(2) >> (4-1))) % 16.U) ^ (((incol_1_2(3) << 2) | (incol_1_2(3) >> (4-2))) % 16.U)
        mixcolumns_block(7) := (((incol_1_3(0) << 1) | (incol_1_3(0) >> (4-1))) % 16.U) ^ (((incol_1_3(2) << 1) | (incol_1_3(2) >> (4-1))) % 16.U) ^ (((incol_1_3(3) << 2) | (incol_1_3(3) >> (4-2))) % 16.U)
        mixcolumns_block(8) := (((incol_1_0(0) << 2) | (incol_1_0(0) >> (4-2))) % 16.U) ^ (((incol_1_0(1) << 1) | (incol_1_0(1) >> (4-1))) % 16.U) ^ (((incol_1_0(3) << 1) | (incol_1_0(3) >> (4-1))) % 16.U)
        mixcolumns_block(9) := (((incol_1_1(0) << 2) | (incol_1_1(0) >> (4-2))) % 16.U) ^ (((incol_1_1(1) << 1) | (incol_1_1(1) >> (4-1))) % 16.U) ^ (((incol_1_1(3) << 1) | (incol_1_1(3) >> (4-1))) % 16.U)
        mixcolumns_block(10) := (((incol_1_2(0) << 2) | (incol_1_2(0) >> (4-2))) % 16.U) ^ (((incol_1_2(1) << 1) | (incol_1_2(1) >> (4-1))) % 16.U) ^ (((incol_1_2(3) << 1) | (incol_1_2(3) >> (4-1))) % 16.U)
        mixcolumns_block(11) := (((incol_1_3(0) << 2) | (incol_1_3(0) >> (4-2))) % 16.U) ^ (((incol_1_3(1) << 1) | (incol_1_3(1) >> (4-1))) % 16.U) ^ (((incol_1_3(3) << 1) | (incol_1_3(3) >> (4-1))) % 16.U)
        mixcolumns_block(12) := (((incol_1_0(0) << 1) | (incol_1_0(0) >> (4-1))) % 16.U) ^ (((incol_1_0(1) << 2) | (incol_1_0(1) >> (4-2))) % 16.U) ^ (((incol_1_0(2) << 1) | (incol_1_0(2) >> (4-1))) % 16.U)
        mixcolumns_block(13) := (((incol_1_1(0) << 1) | (incol_1_1(0) >> (4-1))) % 16.U) ^ (((incol_1_1(1) << 2) | (incol_1_1(1) >> (4-2))) % 16.U) ^ (((incol_1_1(2) << 1) | (incol_1_1(2) >> (4-1))) % 16.U)
        mixcolumns_block(14) := (((incol_1_2(0) << 1) | (incol_1_2(0) >> (4-1))) % 16.U) ^ (((incol_1_2(1) << 2) | (incol_1_2(1) >> (4-2))) % 16.U) ^ (((incol_1_2(2) << 1) | (incol_1_2(2) >> (4-1))) % 16.U)
        mixcolumns_block(15) := (((incol_1_3(0) << 1) | (incol_1_3(0) >> (4-1))) % 16.U) ^ (((incol_1_3(1) << 2) | (incol_1_3(1) >> (4-2))) % 16.U) ^ (((incol_1_3(2) << 1) | (incol_1_3(2) >> (4-1))) % 16.U)

        /*
        mixcolumns_block(0) := outcol_1_0(0)
        mixcolumns_block(1) := outcol_1_1(0)
        mixcolumns_block(2) := outcol_1_2(0)
        mixcolumns_block(3) := outcol_1_3(0)
        mixcolumns_block(4) := outcol_1_0(1)
        mixcolumns_block(5) := outcol_1_1(1)
        mixcolumns_block(6) := outcol_1_2(1)
        mixcolumns_block(7) := outcol_1_3(1)
        mixcolumns_block(8) := outcol_1_0(2)
        mixcolumns_block(9) := outcol_1_1(2)
        mixcolumns_block(10) := outcol_1_2(2)
        mixcolumns_block(11) := outcol_1_3(2)
        mixcolumns_block(12) := outcol_1_0(3)
        mixcolumns_block(13) := outcol_1_1(3)
        mixcolumns_block(14) := outcol_1_2(3)
        mixcolumns_block(15) := outcol_1_3(3)*/
        //printf(p"mixcolumns_block : $mixcolumns_block\n")

        permuted_state_block(0) := mixcolumns_block(state_permutation_inv(0))
        permuted_state_block(1) := mixcolumns_block(state_permutation_inv(1))
        permuted_state_block(2) := mixcolumns_block(state_permutation_inv(2))
        permuted_state_block(3) := mixcolumns_block(state_permutation_inv(3))
        permuted_state_block(4) := mixcolumns_block(state_permutation_inv(4))
        permuted_state_block(5) := mixcolumns_block(state_permutation_inv(5))
        permuted_state_block(6) := mixcolumns_block(state_permutation_inv(6))
        permuted_state_block(7) := mixcolumns_block(state_permutation_inv(7))
        permuted_state_block(8) := mixcolumns_block(state_permutation_inv(8))
        permuted_state_block(9) := mixcolumns_block(state_permutation_inv(9))
        permuted_state_block(10) := mixcolumns_block(state_permutation_inv(10))
        permuted_state_block(11) := mixcolumns_block(state_permutation_inv(11))
        permuted_state_block(12) := mixcolumns_block(state_permutation_inv(12))
        permuted_state_block(13) := mixcolumns_block(state_permutation_inv(13))
        permuted_state_block(14) := mixcolumns_block(state_permutation_inv(14))
        permuted_state_block(15) := mixcolumns_block(state_permutation_inv(15))
        //printf(p"permuted_state_block : $permuted_state_block\n")

        xor_block_2(0) := permuted_state_block(0) ^ block_tweakey(0)
        xor_block_2(1) := permuted_state_block(1) ^ block_tweakey(1)
        xor_block_2(2) := permuted_state_block(2) ^ block_tweakey(2)
        xor_block_2(3) := permuted_state_block(3) ^ block_tweakey(3)
        xor_block_2(4) := permuted_state_block(4) ^ block_tweakey(4)
        xor_block_2(5) := permuted_state_block(5) ^ block_tweakey(5)
        xor_block_2(6) := permuted_state_block(6) ^ block_tweakey(6)
        xor_block_2(7) := permuted_state_block(7) ^ block_tweakey(7)
        xor_block_2(8) := permuted_state_block(8) ^ block_tweakey(8)
        xor_block_2(9) := permuted_state_block(9) ^ block_tweakey(9)
        xor_block_2(10) := permuted_state_block(10) ^ block_tweakey(10)
        xor_block_2(11) := permuted_state_block(11) ^ block_tweakey(11)
        xor_block_2(12) := permuted_state_block(12) ^ block_tweakey(12)
        xor_block_2(13) := permuted_state_block(13) ^ block_tweakey(13)
        xor_block_2(14) := permuted_state_block(14) ^ block_tweakey(14)
        xor_block_2(15) := permuted_state_block(15) ^ block_tweakey(15)
        //printf(p"xor_block_2 : $xor_block_2\n")


        return_block := xor_block_2
    }

    //printf(p"return_block : $return_block\n")
    io.round_state := (return_block(0) << (15-0)*4) + (return_block(1) << (15-1)*4) + (return_block(2) << (15-2)*4) + (return_block(3) << (15-3)*4) + (return_block(4) << (15-4)*4) + (return_block(5) << (15-5)*4) + (return_block(6) << (15-6)*4) + (return_block(7) << (15-7)*4) + (return_block(8) << (15-8)*4) + (return_block(9) << (15-9)*4) + (return_block(10) << (15-10)*4) + (return_block(11) << (15-11)*4) + (return_block(12) << (15-12)*4) + (return_block(13) << (15-13)*4) + (return_block(14) << (15-14)*4) + (return_block(15) << (15-15)*4)
}

class Round_0rounds() extends Module{

val io = IO(new Bundle {
    //Inputs
    val state = Input(UInt(64.W)) 
    val tweakey = Input(UInt(64.W)) 
    val backwards = Input(Bool())
    //rounds are fixed at 5
    //Outputs
    val round_state = Output(UInt(64.W)) 

  }) //input bundle ends here
    val state_permutation = RegInit(VecInit(0.U(64.W), 11.U(64.W), 6.U(64.W), 13.U(64.W), 10.U(64.W), 1.U(64.W), 12.U(64.W), 7.U(64.W), 5.U(64.W), 14.U(64.W), 3.U(64.W), 8.U(64.W), 15.U(64.W), 4.U(64.W), 9.U(64.W), 2.U(64.W)))
    val state_permutation_inv = RegInit(VecInit(0.U(64.W), 5.U(64.W), 15.U(64.W), 10.U(64.W), 13.U(64.W), 8.U(64.W), 2.U(64.W), 7.U(64.W), 11.U(64.W), 14.U(64.W), 4.U(64.W), 1.U(64.W), 6.U(64.W), 3.U(64.W), 9.U(64.W), 12.U(64.W)))
    val used_sbox = RegInit(VecInit(0.U(64.W), 14.U(64.W), 2.U(64.W), 10.U(64.W), 9.U(64.W), 15.U(64.W), 8.U(64.W), 11.U(64.W), 6.U(64.W), 4.U(64.W), 3.U(64.W), 7.U(64.W), 13.U(64.W), 12.U(64.W), 1.U(64.W), 5.U(64.W)))
    val used_sbox_inv = used_sbox
    val block_state = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweakey = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val xor_block_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val subbytes_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val return_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

    for (j <- 0 to 15) //hex to block
    {
        block_state(15-j) := ((io.state & ("hF".U << j*4)) >> j*4)
        block_tweakey(15-j) := ((io.tweakey & ("hF".U << j*4)) >> j*4)
    }

    when(!io.backwards)
    {
        xor_block_1(0) := block_state(0) ^ block_tweakey(0)
        xor_block_1(1) := block_state(1) ^ block_tweakey(1)
        xor_block_1(2) := block_state(2) ^ block_tweakey(2)
        xor_block_1(3) := block_state(3) ^ block_tweakey(3)
        xor_block_1(4) := block_state(4) ^ block_tweakey(4)
        xor_block_1(5) := block_state(5) ^ block_tweakey(5)
        xor_block_1(6) := block_state(6) ^ block_tweakey(6)
        xor_block_1(7) := block_state(7) ^ block_tweakey(7)
        xor_block_1(8) := block_state(8) ^ block_tweakey(8)
        xor_block_1(9) := block_state(9) ^ block_tweakey(9)
        xor_block_1(10) := block_state(10) ^ block_tweakey(10)
        xor_block_1(11) := block_state(11) ^ block_tweakey(11)
        xor_block_1(12) := block_state(12) ^ block_tweakey(12)
        xor_block_1(13) := block_state(13) ^ block_tweakey(13)
        xor_block_1(14) := block_state(14) ^ block_tweakey(14)
        xor_block_1(15) := block_state(15) ^ block_tweakey(15)


        return_block := xor_block_1

    }
    .otherwise
    {
        subbytes_block(0) := used_sbox_inv(block_state(0))
        subbytes_block(1) := used_sbox_inv(block_state(1))
        subbytes_block(2) := used_sbox_inv(block_state(2))
        subbytes_block(3) := used_sbox_inv(block_state(3))
        subbytes_block(4) := used_sbox_inv(block_state(4))
        subbytes_block(5) := used_sbox_inv(block_state(5))
        subbytes_block(6) := used_sbox_inv(block_state(6))
        subbytes_block(7) := used_sbox_inv(block_state(7))
        subbytes_block(8) := used_sbox_inv(block_state(8))
        subbytes_block(9) := used_sbox_inv(block_state(9))
        subbytes_block(10) := used_sbox_inv(block_state(10))
        subbytes_block(11) := used_sbox_inv(block_state(11))
        subbytes_block(12) := used_sbox_inv(block_state(12))
        subbytes_block(13) := used_sbox_inv(block_state(13))
        subbytes_block(14) := used_sbox_inv(block_state(14))
        subbytes_block(15) := used_sbox_inv(block_state(15))

        return_block := subbytes_block
    }

    //printf(p"return_block : $return_block\n")
    io.round_state := (return_block(0) << (15-0)*4) + (return_block(1) << (15-1)*4) + (return_block(2) << (15-2)*4) + (return_block(3) << (15-3)*4) + (return_block(4) << (15-4)*4) + (return_block(5) << (15-5)*4) + (return_block(6) << (15-6)*4) + (return_block(7) << (15-7)*4) + (return_block(8) << (15-8)*4) + (return_block(9) << (15-9)*4) + (return_block(10) << (15-10)*4) + (return_block(11) << (15-11)*4) + (return_block(12) << (15-12)*4) + (return_block(13) << (15-13)*4) + (return_block(14) << (15-14)*4) + (return_block(15) << (15-15)*4)
}


class MiddleRound() extends Module{

val io = IO(new Bundle {
    //Inputs
    val state = Input(UInt(64.W)) 
    val k1 = Input(UInt(64.W)) 
    //Outputs
    val middleround_state = Output(UInt(64.W)) 

  }) //input bundle ends here

    val state_permutation = RegInit(VecInit(0.U(64.W), 11.U(64.W), 6.U(64.W), 13.U(64.W), 10.U(64.W), 1.U(64.W), 12.U(64.W), 7.U(64.W), 5.U(64.W), 14.U(64.W), 3.U(64.W), 8.U(64.W), 15.U(64.W), 4.U(64.W), 9.U(64.W), 2.U(64.W)))
    val state_permutation_inv = RegInit(VecInit(0.U(64.W), 5.U(64.W), 15.U(64.W), 10.U(64.W), 13.U(64.W), 8.U(64.W), 2.U(64.W), 7.U(64.W), 11.U(64.W), 14.U(64.W), 4.U(64.W), 1.U(64.W), 6.U(64.W), 3.U(64.W), 9.U(64.W), 12.U(64.W)))
    val used_sbox = RegInit(VecInit(0.U(64.W), 14.U(64.W), 2.U(64.W), 10.U(64.W), 9.U(64.W), 15.U(64.W), 8.U(64.W), 11.U(64.W), 6.U(64.W), 4.U(64.W), 3.U(64.W), 7.U(64.W), 13.U(64.W), 12.U(64.W), 1.U(64.W), 5.U(64.W)))
    val used_sbox_inv = used_sbox 
    

    val block_state = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_k1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val permuted_state_false_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val mixcolumns_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val xor_block_1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val permuted_state_true_block = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))


    val incol_0_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_0_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_0_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_0_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_0_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_0_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_0_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_0_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))


    for (j <- 0 to 15) //hex to block
    {
        block_state(15-j) := ((io.state & ("hF".U << j*4)) >> j*4)
        block_k1(15-j) := ((io.k1 & ("hF".U << j*4)) >> j*4)
    }


    permuted_state_false_block(0) := block_state(state_permutation(0))
    permuted_state_false_block(1) := block_state(state_permutation(1))
    permuted_state_false_block(2) := block_state(state_permutation(2))
    permuted_state_false_block(3) := block_state(state_permutation(3))
    permuted_state_false_block(4) := block_state(state_permutation(4))
    permuted_state_false_block(5) := block_state(state_permutation(5))
    permuted_state_false_block(6) := block_state(state_permutation(6))
    permuted_state_false_block(7) := block_state(state_permutation(7))
    permuted_state_false_block(8) := block_state(state_permutation(8))
    permuted_state_false_block(9) := block_state(state_permutation(9))
    permuted_state_false_block(10) := block_state(state_permutation(10))
    permuted_state_false_block(11) := block_state(state_permutation(11))
    permuted_state_false_block(12) := block_state(state_permutation(12))
    permuted_state_false_block(13) := block_state(state_permutation(13))
    permuted_state_false_block(14) := block_state(state_permutation(14))
    permuted_state_false_block(15) := block_state(state_permutation(15))

    incol_0_0(0) := permuted_state_false_block(0)
    incol_0_0(1) := permuted_state_false_block(4)
    incol_0_0(2) := permuted_state_false_block(8)
    incol_0_0(3) := permuted_state_false_block(12)

    incol_0_1(0) := permuted_state_false_block(0+1)
    incol_0_1(1) := permuted_state_false_block(4+1)
    incol_0_1(2) := permuted_state_false_block(8+1)
    incol_0_1(3) := permuted_state_false_block(12+1)
        
    incol_0_2(0) := permuted_state_false_block(0+2)
    incol_0_2(1) := permuted_state_false_block(4+2)
    incol_0_2(2) := permuted_state_false_block(8+2)
    incol_0_2(3) := permuted_state_false_block(12+2)

    incol_0_3(0) := permuted_state_false_block(0+3)
    incol_0_3(1) := permuted_state_false_block(4+3)
    incol_0_3(2) := permuted_state_false_block(8+3)
    incol_0_3(3) := permuted_state_false_block(12+3)

    outcol_0_0(0) := (((incol_0_0(1) << 1) | (incol_0_0(1) >> (4-1))) % 16.U) ^ (((incol_0_0(2) << 2) | (incol_0_0(2) >> (4-2))) % 16.U) ^ (((incol_0_0(3) << 1) | (incol_0_0(3) >> (4-1))) % 16.U)
    outcol_0_0(1) := (((incol_0_0(0) << 1) | (incol_0_0(0) >> (4-1))) % 16.U) ^ (((incol_0_0(2) << 1) | (incol_0_0(2) >> (4-1))) % 16.U) ^ (((incol_0_0(3) << 2) | (incol_0_0(3) >> (4-2))) % 16.U)
    outcol_0_0(2) := (((incol_0_0(0) << 2) | (incol_0_0(0) >> (4-2))) % 16.U) ^ (((incol_0_0(1) << 1) | (incol_0_0(1) >> (4-1))) % 16.U) ^ (((incol_0_0(3) << 1) | (incol_0_0(3) >> (4-1))) % 16.U)
    outcol_0_0(3) := (((incol_0_0(0) << 1) | (incol_0_0(0) >> (4-1))) % 16.U) ^ (((incol_0_0(1) << 2) | (incol_0_0(1) >> (4-2))) % 16.U) ^ (((incol_0_0(2) << 1) | (incol_0_0(2) >> (4-1))) % 16.U)

    outcol_0_1(0) := (((incol_0_1(1) << 1) | (incol_0_1(1) >> (4-1))) % 16.U) ^ (((incol_0_1(2) << 2) | (incol_0_1(2) >> (4-2))) % 16.U) ^ (((incol_0_1(3) << 1) | (incol_0_1(3) >> (4-1))) % 16.U)
    outcol_0_1(1) := (((incol_0_1(0) << 1) | (incol_0_1(0) >> (4-1))) % 16.U) ^ (((incol_0_1(2) << 1) | (incol_0_1(2) >> (4-1))) % 16.U) ^ (((incol_0_1(3) << 2) | (incol_0_1(3) >> (4-2))) % 16.U)
    outcol_0_1(2) := (((incol_0_1(0) << 2) | (incol_0_1(0) >> (4-2))) % 16.U) ^ (((incol_0_1(1) << 1) | (incol_0_1(1) >> (4-1))) % 16.U) ^ (((incol_0_1(3) << 1) | (incol_0_1(3) >> (4-1))) % 16.U)
    outcol_0_1(3) := (((incol_0_1(0) << 1) | (incol_0_1(0) >> (4-1))) % 16.U) ^ (((incol_0_1(1) << 2) | (incol_0_1(1) >> (4-2))) % 16.U) ^ (((incol_0_1(2) << 1) | (incol_0_1(2) >> (4-1))) % 16.U)

    outcol_0_2(0) := (((incol_0_2(1) << 1) | (incol_0_2(1) >> (4-1))) % 16.U) ^ (((incol_0_2(2) << 2) | (incol_0_2(2) >> (4-2))) % 16.U) ^ (((incol_0_2(3) << 1) | (incol_0_2(3) >> (4-1))) % 16.U)
    outcol_0_2(1) := (((incol_0_2(0) << 1) | (incol_0_2(0) >> (4-1))) % 16.U) ^ (((incol_0_2(2) << 1) | (incol_0_2(2) >> (4-1))) % 16.U) ^ (((incol_0_2(3) << 2) | (incol_0_2(3) >> (4-2))) % 16.U)
    outcol_0_2(2) := (((incol_0_2(0) << 2) | (incol_0_2(0) >> (4-2))) % 16.U) ^ (((incol_0_2(1) << 1) | (incol_0_2(1) >> (4-1))) % 16.U) ^ (((incol_0_2(3) << 1) | (incol_0_2(3) >> (4-1))) % 16.U)
    outcol_0_2(3) := (((incol_0_2(0) << 1) | (incol_0_2(0) >> (4-1))) % 16.U) ^ (((incol_0_2(1) << 2) | (incol_0_2(1) >> (4-2))) % 16.U) ^ (((incol_0_2(2) << 1) | (incol_0_2(2) >> (4-1))) % 16.U)

    outcol_0_3(0) := (((incol_0_3(1) << 1) | (incol_0_3(1) >> (4-1))) % 16.U) ^ (((incol_0_3(2) << 2) | (incol_0_3(2) >> (4-2))) % 16.U) ^ (((incol_0_3(3) << 1) | (incol_0_3(3) >> (4-1))) % 16.U)
    outcol_0_3(1) := (((incol_0_3(0) << 1) | (incol_0_3(0) >> (4-1))) % 16.U) ^ (((incol_0_3(2) << 1) | (incol_0_3(2) >> (4-1))) % 16.U) ^ (((incol_0_3(3) << 2) | (incol_0_3(3) >> (4-2))) % 16.U)
    outcol_0_3(2) := (((incol_0_3(0) << 2) | (incol_0_3(0) >> (4-2))) % 16.U) ^ (((incol_0_3(1) << 1) | (incol_0_3(1) >> (4-1))) % 16.U) ^ (((incol_0_3(3) << 1) | (incol_0_3(3) >> (4-1))) % 16.U)
    outcol_0_3(3) := (((incol_0_3(0) << 1) | (incol_0_3(0) >> (4-1))) % 16.U) ^ (((incol_0_3(1) << 2) | (incol_0_3(1) >> (4-2))) % 16.U) ^ (((incol_0_3(2) << 1) | (incol_0_3(2) >> (4-1))) % 16.U)

    mixcolumns_block(0) := outcol_0_0(0)
    mixcolumns_block(1) := outcol_0_1(0)
    mixcolumns_block(2) := outcol_0_2(0)
    mixcolumns_block(3) := outcol_0_3(0)
    mixcolumns_block(4) := outcol_0_0(1)
    mixcolumns_block(5) := outcol_0_1(1)
    mixcolumns_block(6) := outcol_0_2(1)
    mixcolumns_block(7) := outcol_0_3(1)
    mixcolumns_block(8) := outcol_0_0(2)
    mixcolumns_block(9) := outcol_0_1(2)
    mixcolumns_block(10) := outcol_0_2(2)
    mixcolumns_block(11) := outcol_0_3(2)
    mixcolumns_block(12) := outcol_0_0(3)
    mixcolumns_block(13) := outcol_0_1(3)
    mixcolumns_block(14) := outcol_0_2(3)
    mixcolumns_block(15) := outcol_0_3(3)

    xor_block_1(0) := mixcolumns_block(0) ^ block_k1(0)
    xor_block_1(1) := mixcolumns_block(1) ^ block_k1(1)
    xor_block_1(2) := mixcolumns_block(2) ^ block_k1(2)
    xor_block_1(3) := mixcolumns_block(3) ^ block_k1(3)
    xor_block_1(4) := mixcolumns_block(4) ^ block_k1(4)
    xor_block_1(5) := mixcolumns_block(5) ^ block_k1(5)
    xor_block_1(6) := mixcolumns_block(6) ^ block_k1(6)
    xor_block_1(7) := mixcolumns_block(7) ^ block_k1(7)
    xor_block_1(8) := mixcolumns_block(8) ^ block_k1(8)
    xor_block_1(9) := mixcolumns_block(9) ^ block_k1(9)
    xor_block_1(10) := mixcolumns_block(10) ^ block_k1(10)
    xor_block_1(11) := mixcolumns_block(11) ^ block_k1(11)
    xor_block_1(12) := mixcolumns_block(12) ^ block_k1(12)
    xor_block_1(13) := mixcolumns_block(13) ^ block_k1(13)
    xor_block_1(14) := mixcolumns_block(14) ^ block_k1(14)
    xor_block_1(15) := mixcolumns_block(15) ^ block_k1(15)

    permuted_state_true_block(0) := xor_block_1(state_permutation_inv(0))
    permuted_state_true_block(1) := xor_block_1(state_permutation_inv(1))
    permuted_state_true_block(2) := xor_block_1(state_permutation_inv(2))
    permuted_state_true_block(3) := xor_block_1(state_permutation_inv(3))
    permuted_state_true_block(4) := xor_block_1(state_permutation_inv(4))
    permuted_state_true_block(5) := xor_block_1(state_permutation_inv(5))
    permuted_state_true_block(6) := xor_block_1(state_permutation_inv(6))
    permuted_state_true_block(7) := xor_block_1(state_permutation_inv(7))
    permuted_state_true_block(8) := xor_block_1(state_permutation_inv(8))
    permuted_state_true_block(9) := xor_block_1(state_permutation_inv(9))
    permuted_state_true_block(10) := xor_block_1(state_permutation_inv(10))
    permuted_state_true_block(11) := xor_block_1(state_permutation_inv(11))
    permuted_state_true_block(12) := xor_block_1(state_permutation_inv(12))
    permuted_state_true_block(13) := xor_block_1(state_permutation_inv(13))
    permuted_state_true_block(14) := xor_block_1(state_permutation_inv(14))
    permuted_state_true_block(15) := xor_block_1(state_permutation_inv(15))


    io.middleround_state := (permuted_state_true_block(0) << (15-0)*4) + (permuted_state_true_block(1) << (15-1)*4) + (permuted_state_true_block(2) << (15-2)*4) + (permuted_state_true_block(3) << (15-3)*4) + (permuted_state_true_block(4) << (15-4)*4) + (permuted_state_true_block(5) << (15-5)*4) + (permuted_state_true_block(6) << (15-6)*4) + (permuted_state_true_block(7) << (15-7)*4) + (permuted_state_true_block(8) << (15-8)*4) + (permuted_state_true_block(9) << (15-9)*4) + (permuted_state_true_block(10) << (15-10)*4) + (permuted_state_true_block(11) << (15-11)*4) + (permuted_state_true_block(12) << (15-12)*4) + (permuted_state_true_block(13) << (15-13)*4) + (permuted_state_true_block(14) << (15-14)*4) + (permuted_state_true_block(15) << (15-15)*4)
}
///////////////////////////////////////////////////////////
//        This is where the final qarma64 function is
///////////////////////////////////////////////////////////
class Qarma64() extends Module{

val io = IO(new Bundle {
    //Inputs
    val plaintext = Input(UInt(64.W)) 
    val tweak = Input(UInt(64.W)) 
    val w0 = Input(UInt(64.W)) 
    val k0 = Input(UInt(64.W)) //here the key is basically concatenation of w0 and k0. But to simplify we will be using them separately. You can break it up in the circuitry leading upto this module.
    val encryption = Input(Bool()) 
    //Outputs
    val ciphertext = Output(UInt(64.W)) 

  }) //input bundle ends here

    val w1 = Wire(UInt(64.W))
    w1 := ((io.w0 >> 1) | ((io.w0 & 1.U(64.W)) << 63)) ^ (io.w0 >> 63)

    val block_plaintext = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweak = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_w0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_w1 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_k0 = WireInit(VecInit(Seq.fill(16)(0.U(64.W))))

    for (j <- 0 to 15) //hex to block
    {
        block_plaintext(15-j) := ((io.plaintext & ("hF".U << j*4)) >> j*4)
        block_tweak(15-j) := ((io.tweak & ("hF".U << j*4)) >> j*4)
        block_w0(15-j) := ((io.w0 & ("hF".U << j*4)) >> j*4)
        block_w1(15-j) := ((w1 & ("hF".U << j*4)) >> j*4)
        block_k0(15-j) := ((io.k0 & ("hF".U << j*4)) >> j*4)
    }

    printf(p"block_plaintext:$block_plaintext\n block_tweak:$block_tweak\n block_w0:$block_w0\n block_w1:$block_w1\n block_k0:$block_k0\n")
    io.ciphertext := RegNext(io.plaintext) //temporary just to check the module
}