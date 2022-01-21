//package qarma64

import chisel3._
import chisel3.util._

class PermuteTweak() extends Module {
val io = IO(new Bundle {
    //Inputs
    val tweak = Input(UInt(64.W)) 

    //Outputs
    val permuted_tweak = Output(UInt(64.W)) 

  }) //input bundle ends here

    val tweak_permutation = RegInit(VecInit(6.U(64.W), 5.U(64.W), 14.U(64.W), 15.U(64.W), 0.U(64.W), 1.U(64.W), 2.U(64.W), 3.U(64.W), 7.U(64.W), 12.U(64.W), 13.U(64.W), 4.U(64.W), 8.U(64.W), 9.U(64.W), 10.U(64.W), 11.U(64.W)))
    //val block_tweak = RegInit(VecInit(6.U(4.W), 5.U(4.W), 14.U(4.W), 15.U(4.W), 0.U(4.W), 1.U(4.W), 2.U(4.W), 3.U(4.W), 7.U(4.W), 12.U(4.W), 13.U(4.W), 4.U(4.W), 8.U(4.W), 9.U(4.W), 10.U(4.W), 11.U(4.W)))

    //val block_tweak = VecInit(0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W))
    val block_tweak = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_tweak = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))
    //val block_permuted_tweak = VecInit(0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W), 0.U(4.W))

    //printf(p"tweak_permutation : $tweak_permutation\n")
    //printf(p"Initial block_tweak: $block_tweak\n")
    //printf(p"Initial block_permuted_tweak: $block_permuted_tweak\n")
    //printf(p"io.tweak: ${io.tweak} \n")
    //need to convert hex tweak to block
    for (j <- 0 to 15) //hex to block
    {
        block_tweak(15-j) := ((io.tweak & ("hF".U << j*4)) >> j*4)
        //block_tweak(j) := 1.U(4.W)
        //printf(p"Iteration: $j | Block is ${block_tweak(j)}\n")
    }
    //printf(p"At this point block_tweak is $block_tweak\n")
    val return_value = Wire(UInt(64.W))
    //val temp_value = Wire(UInt(64.W))
    return_value := 0.U(64.W)

    //block to hex
    return_value := (block_tweak(tweak_permutation(0)) << (15-0)*4) + (block_tweak(tweak_permutation(1)) << (15-1)*4) + (block_tweak(tweak_permutation(2)) << (15-2)*4) + (block_tweak(tweak_permutation(3)) << (15-3)*4) + (block_tweak(tweak_permutation(4)) << (15-4)*4) + (block_tweak(tweak_permutation(5)) << (15-5)*4) + (block_tweak(tweak_permutation(6)) << (15-6)*4) + (block_tweak(tweak_permutation(7)) << (15-7)*4) + (block_tweak(tweak_permutation(8)) << (15-8)*4) + (block_tweak(tweak_permutation(9)) << (15-9)*4) + (block_tweak(tweak_permutation(10)) << (15-10)*4) + (block_tweak(tweak_permutation(11)) << (15-11)*4) + (block_tweak(tweak_permutation(12)) << (15-12)*4) + (block_tweak(tweak_permutation(13)) << (15-13)*4) + (block_tweak(tweak_permutation(14)) << (15-14)*4) + (block_tweak(tweak_permutation(15)) << (15-15)*4)



    //return_value := (block_tweak(tweak_permutation(j)) << (15-j)*4))
    //printf(p"Final return_value : $return_value\n")
    //printf(p"At this point block_permuted_tweak is $block_permuted_tweak\n")
   

    io.permuted_tweak := return_value
}

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

class CalcTweak(param_rounds: Int) extends Module {
val io = IO(new Bundle {
    //Inputs
    val tweak = Input(UInt(64.W)) 
    //val r = Input(UInt(64.W))
    //Outputs
    val tweak_r = Output(UInt(64.W)) 

  }) //input bundle ends here
    //need to call the other functions here
    //val rounds = UInt(64.W)
    val PermuteTweakModule = Module(new PermuteTweak)
    val TweakLFSRModule = Module (new TweakLFSR)
    val temp_tweak = Wire(UInt(64.W))
    temp_tweak := io.tweak & "hffffffffffffffff".U  
    printf(p"io.tweak is:  ${io.tweak}\n")
    printf(p"temp_tweak at this point is:  $temp_tweak\n")
    //rounds := io.r.litValue
    for (i <- 0 until param_rounds)
    {
        
        PermuteTweakModule.io.tweak := temp_tweak
        //printf(p"PermuteTweak input: ${PermuteTweakModule.io.tweak}\n")
        TweakLFSRModule.io.tweak := PermuteTweakModule.io.permuted_tweak
        temp_tweak := TweakLFSRModule.io.lfsr
    }

    //printf(p"temp_tweak at this point is:  $temp_tweak\n")

    io.tweak_r := temp_tweak
}