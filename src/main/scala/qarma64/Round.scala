import chisel3._
import chisel3.util._

class Round_prototype() extends Module{

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