package qarma64

import chisel3._
import chisel3.util._



class TweakLFSR() extends Module {
val io = IO(new Bundle {
    //Inputs
    val tweak = Input(UInt(64.W)) 
    //Outputs
    val lfsr = Output(UInt(64.W)) 

  }) //input bundle ends here

    val return_value = Wire(UInt(64.W))

    val block_tweak = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_tweak_result = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))
    for (j <- 0 to 15) //hex to block
    {
        block_tweak(15-j) := ((io.tweak & ("hF".U << j*4)) >> j*4)
    }
    //b3,b2,b1,b0 = (t>>3)&1,(t>>2)&1,(t>>1)&1,(t>>0)&1
    //b3    (t>>3)&1    ((block_tweak(i)>>3)&1.U)
    //b2    (t>>2)&1,   ((block_tweak(i)>>2)&1.U)
    //b1    (t>>1)&1,   ((block_tweak(i)>>1)&1.U)
    //b0    (t>>0)&1    ((block_tweak(i)>>0)&1.U)
    block_tweak_result(0):= ((((block_tweak(0)>>0)&1.U)^((block_tweak(0)>>1)&1.U))<<3) | (((block_tweak(0)>>3)&1.U)<<2) | (((block_tweak(0)>>2)&1.U)<<1) | ((block_tweak(0)>>1)&1.U)
    block_tweak_result(1):= ((((block_tweak(1)>>0)&1.U)^((block_tweak(1)>>1)&1.U))<<3) | (((block_tweak(1)>>3)&1.U)<<2) | (((block_tweak(1)>>2)&1.U)<<1) | ((block_tweak(1)>>1)&1.U)
    block_tweak_result(2):= block_tweak(2)
    block_tweak_result(3):= ((((block_tweak(3)>>0)&1.U)^((block_tweak(3)>>1)&1.U))<<3) | (((block_tweak(3)>>3)&1.U)<<2) | (((block_tweak(3)>>2)&1.U)<<1) | ((block_tweak(3)>>1)&1.U)
    block_tweak_result(4):= ((((block_tweak(4)>>0)&1.U)^((block_tweak(4)>>1)&1.U))<<3) | (((block_tweak(4)>>3)&1.U)<<2) | (((block_tweak(4)>>2)&1.U)<<1) | ((block_tweak(4)>>1)&1.U)
    block_tweak_result(5):= block_tweak(5)
    block_tweak_result(6):= block_tweak(6)
    block_tweak_result(7):= block_tweak(7)
    block_tweak_result(8):= ((((block_tweak(8)>>0)&1.U)^((block_tweak(8)>>1)&1.U))<<3) | (((block_tweak(8)>>3)&1.U)<<2) | (((block_tweak(8)>>2)&1.U)<<1) | ((block_tweak(8)>>1)&1.U)
    block_tweak_result(9):= block_tweak(9)
    block_tweak_result(10):= block_tweak(10)
    block_tweak_result(11):= ((((block_tweak(11)>>0)&1.U)^((block_tweak(11)>>1)&1.U))<<3) | (((block_tweak(11)>>3)&1.U)<<2) | (((block_tweak(11)>>2)&1.U)<<1) | ((block_tweak(11)>>1)&1.U)
    block_tweak_result(12):= block_tweak(12)
    block_tweak_result(13):= ((((block_tweak(13)>>0)&1.U)^((block_tweak(13)>>1)&1.U))<<3) | (((block_tweak(13)>>3)&1.U)<<2) | (((block_tweak(13)>>2)&1.U)<<1) | ((block_tweak(13)>>1)&1.U)
    block_tweak_result(14):= block_tweak(14)
    block_tweak_result(15):= block_tweak(15)

    return_value := (block_tweak_result(0) << (15-0)*4) + (block_tweak_result(1) << (15-1)*4) + (block_tweak_result(2) << (15-2)*4) + (block_tweak_result(3) << (15-3)*4) + (block_tweak_result(4) << (15-4)*4) + (block_tweak_result(5) << (15-5)*4) + (block_tweak_result(6) << (15-6)*4) + (block_tweak_result(7) << (15-7)*4) + (block_tweak_result(8) << (15-8)*4) + (block_tweak_result(9) << (15-9)*4) + (block_tweak_result(10) << (15-10)*4) + (block_tweak_result(11) << (15-11)*4) + (block_tweak_result(12) << (15-12)*4) + (block_tweak_result(13) << (15-13)*4) + (block_tweak_result(14) << (15-14)*4) + (block_tweak_result(15) << (15-15)*4)

    io.lfsr := return_value
}