package qarma64

import chisel3._
import chisel3.util._



class MixColumns() extends Module {
val io = IO(new Bundle {
    //Inputs
    val state = Input(UInt(64.W)) 
    //Outputs
    val mixed_state = Output(UInt(64.W)) 

  }) //input bundle ends here

    val block_state = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_mixed_state = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))

    val return_value = Wire(UInt(64.W))

    for (j <- 0 to 15) //hex to block
    {
        block_state(15-j) := ((io.state & ("hF".U << j*4)) >> j*4)
    }
    
    val incol_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val incol_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))

    val outcol_0 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_1 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_2 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))
    val outcol_3 = RegInit(VecInit(Seq.fill(4)(0.U(64.W))))

    incol_0(0) := block_state(0)
    incol_0(1) := block_state(4)
    incol_0(2) := block_state(8)
    incol_0(3) := block_state(12)

    incol_1(0) := block_state(0+1)
    incol_1(1) := block_state(4+1)
    incol_1(2) := block_state(8+1)
    incol_1(3) := block_state(12+1)
    
    incol_2(0) := block_state(0+2)
    incol_2(1) := block_state(4+2)
    incol_2(2) := block_state(8+2)
    incol_2(3) := block_state(12+2)

    incol_3(0) := block_state(0+3)
    incol_3(1) := block_state(4+3)
    incol_3(2) := block_state(8+3)
    incol_3(3) := block_state(12+3)


    outcol_0(0) := (((incol_0(1) << 1) | (incol_0(1) >> (4-1))) % 16.U) ^ (((incol_0(2) << 2) | (incol_0(2) >> (4-2))) % 16.U) ^ (((incol_0(3) << 1) | (incol_0(3) >> (4-1))) % 16.U)
    outcol_0(1) := (((incol_0(0) << 1) | (incol_0(0) >> (4-1))) % 16.U) ^ (((incol_0(2) << 1) | (incol_0(2) >> (4-1))) % 16.U) ^ (((incol_0(3) << 2) | (incol_0(3) >> (4-2))) % 16.U)
    outcol_0(2) := (((incol_0(0) << 2) | (incol_0(0) >> (4-2))) % 16.U) ^ (((incol_0(1) << 1) | (incol_0(1) >> (4-1))) % 16.U) ^ (((incol_0(3) << 1) | (incol_0(3) >> (4-1))) % 16.U)
    outcol_0(3) := (((incol_0(0) << 1) | (incol_0(0) >> (4-1))) % 16.U) ^ (((incol_0(1) << 2) | (incol_0(1) >> (4-2))) % 16.U) ^ (((incol_0(2) << 1) | (incol_0(2) >> (4-1))) % 16.U)

    outcol_1(0) := (((incol_1(1) << 1) | (incol_1(1) >> (4-1))) % 16.U) ^ (((incol_1(2) << 2) | (incol_1(2) >> (4-2))) % 16.U) ^ (((incol_1(3) << 1) | (incol_1(3) >> (4-1))) % 16.U)
    outcol_1(1) := (((incol_1(0) << 1) | (incol_1(0) >> (4-1))) % 16.U) ^ (((incol_1(2) << 1) | (incol_1(2) >> (4-1))) % 16.U) ^ (((incol_1(3) << 2) | (incol_1(3) >> (4-2))) % 16.U)
    outcol_1(2) := (((incol_1(0) << 2) | (incol_1(0) >> (4-2))) % 16.U) ^ (((incol_1(1) << 1) | (incol_1(1) >> (4-1))) % 16.U) ^ (((incol_1(3) << 1) | (incol_1(3) >> (4-1))) % 16.U)
    outcol_1(3) := (((incol_1(0) << 1) | (incol_1(0) >> (4-1))) % 16.U) ^ (((incol_1(1) << 2) | (incol_1(1) >> (4-2))) % 16.U) ^ (((incol_1(2) << 1) | (incol_1(2) >> (4-1))) % 16.U)

    outcol_2(0) := (((incol_2(1) << 1) | (incol_2(1) >> (4-1))) % 16.U) ^ (((incol_2(2) << 2) | (incol_2(2) >> (4-2))) % 16.U) ^ (((incol_2(3) << 1) | (incol_2(3) >> (4-1))) % 16.U)
    outcol_2(1) := (((incol_2(0) << 1) | (incol_2(0) >> (4-1))) % 16.U) ^ (((incol_2(2) << 1) | (incol_2(2) >> (4-1))) % 16.U) ^ (((incol_2(3) << 2) | (incol_2(3) >> (4-2))) % 16.U)
    outcol_2(2) := (((incol_2(0) << 2) | (incol_2(0) >> (4-2))) % 16.U) ^ (((incol_2(1) << 1) | (incol_2(1) >> (4-1))) % 16.U) ^ (((incol_2(3) << 1) | (incol_2(3) >> (4-1))) % 16.U)
    outcol_2(3) := (((incol_2(0) << 1) | (incol_2(0) >> (4-1))) % 16.U) ^ (((incol_2(1) << 2) | (incol_2(1) >> (4-2))) % 16.U) ^ (((incol_2(2) << 1) | (incol_2(2) >> (4-1))) % 16.U)

    outcol_3(0) := (((incol_3(1) << 1) | (incol_3(1) >> (4-1))) % 16.U) ^ (((incol_3(2) << 2) | (incol_3(2) >> (4-2))) % 16.U) ^ (((incol_3(3) << 1) | (incol_3(3) >> (4-1))) % 16.U)
    outcol_3(1) := (((incol_3(0) << 1) | (incol_3(0) >> (4-1))) % 16.U) ^ (((incol_3(2) << 1) | (incol_3(2) >> (4-1))) % 16.U) ^ (((incol_3(3) << 2) | (incol_3(3) >> (4-2))) % 16.U)
    outcol_3(2) := (((incol_3(0) << 2) | (incol_3(0) >> (4-2))) % 16.U) ^ (((incol_3(1) << 1) | (incol_3(1) >> (4-1))) % 16.U) ^ (((incol_3(3) << 1) | (incol_3(3) >> (4-1))) % 16.U)
    outcol_3(3) := (((incol_3(0) << 1) | (incol_3(0) >> (4-1))) % 16.U) ^ (((incol_3(1) << 2) | (incol_3(1) >> (4-2))) % 16.U) ^ (((incol_3(2) << 1) | (incol_3(2) >> (4-1))) % 16.U)

    block_mixed_state(0) := outcol_0(0)
    block_mixed_state(1) := outcol_1(0)
    block_mixed_state(2) := outcol_2(0)
    block_mixed_state(3) := outcol_3(0)
    block_mixed_state(4) := outcol_0(1)
    block_mixed_state(5) := outcol_1(1)
    block_mixed_state(6) := outcol_2(1)
    block_mixed_state(7) := outcol_3(1)
    block_mixed_state(8) := outcol_0(2)
    block_mixed_state(9) := outcol_1(2)
    block_mixed_state(10) := outcol_2(2)
    block_mixed_state(11) := outcol_3(2)
    block_mixed_state(12) := outcol_0(3)
    block_mixed_state(13) := outcol_1(3)
    block_mixed_state(14) := outcol_2(3)
    block_mixed_state(15) := outcol_3(3)

    return_value := 0.U(64.W)

    //debug prints
    /*printf(p"block_state: ${block_state}\n")
    printf(p"incol_0: ${incol_0}\n")
    printf(p"outcol_0: ${outcol_0}\n")
    printf(p"block_mixed_state: ${block_mixed_state}\n")
    */
    
    return_value := (block_mixed_state(0) << (15-0)*4) + (block_mixed_state(1) << (15-1)*4) + (block_mixed_state(2) << (15-2)*4) + (block_mixed_state(3) << (15-3)*4) + (block_mixed_state(4) << (15-4)*4) + (block_mixed_state(5) << (15-5)*4) + (block_mixed_state(6) << (15-6)*4) + (block_mixed_state(7) << (15-7)*4) + (block_mixed_state(8) << (15-8)*4) + (block_mixed_state(9) << (15-9)*4) + (block_mixed_state(10) << (15-10)*4) + (block_mixed_state(11) << (15-11)*4) + (block_mixed_state(12) << (15-12)*4) + (block_mixed_state(13) << (15-13)*4) + (block_mixed_state(14) << (15-14)*4) + (block_mixed_state(15) << (15-15)*4)


    io.mixed_state := return_value
}