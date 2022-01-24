import chisel3._
import chisel3.util._


class MiddleRound_prototype() extends Module{

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