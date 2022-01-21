package qarma64

import chisel3._
import chisel3.util._



class PermuteState() extends Module {
val io = IO(new Bundle {
    //Inputs
    val state = Input(UInt(64.W)) 
    val inverse = Input(Bool())
    //Outputs
    val permuted_state = Output(UInt(64.W)) 

  }) //input bundle ends here

    val state_permutation = RegInit(VecInit(0.U(64.W), 11.U(64.W), 6.U(64.W), 13.U(64.W), 10.U(64.W), 1.U(64.W), 12.U(64.W), 7.U(64.W), 5.U(64.W), 14.U(64.W), 3.U(64.W), 8.U(64.W), 15.U(64.W), 4.U(64.W), 9.U(64.W), 2.U(64.W)))
    val state_permutation_inv = RegInit(VecInit(0.U(64.W), 5.U(64.W), 15.U(64.W), 10.U(64.W), 13.U(64.W), 8.U(64.W), 2.U(64.W), 7.U(64.W), 11.U(64.W), 14.U(64.W), 4.U(64.W), 1.U(64.W), 6.U(64.W), 3.U(64.W), 9.U(64.W), 12.U(64.W)))

  
    val block_state = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))
    val block_permuted_state = RegInit(VecInit(Seq.fill(16)(0.U(64.W))))

    val return_value = Wire(UInt(64.W))

    when (io.inverse)
    {
        for (j <- 0 to 15) //hex to block
        {
            block_state(15-j) := ((io.state & ("hF".U << j*4)) >> j*4)
        }
    
        return_value := 0.U(64.W)

        return_value := (block_state(state_permutation_inv(0)) << (15-0)*4) + (block_state(state_permutation_inv(1)) << (15-1)*4) + (block_state(state_permutation_inv(2)) << (15-2)*4) + (block_state(state_permutation_inv(3)) << (15-3)*4) + (block_state(state_permutation_inv(4)) << (15-4)*4) + (block_state(state_permutation_inv(5)) << (15-5)*4) + (block_state(state_permutation_inv(6)) << (15-6)*4) + (block_state(state_permutation_inv(7)) << (15-7)*4) + (block_state(state_permutation_inv(8)) << (15-8)*4) + (block_state(state_permutation_inv(9)) << (15-9)*4) + (block_state(state_permutation_inv(10)) << (15-10)*4) + (block_state(state_permutation_inv(11)) << (15-11)*4) + (block_state(state_permutation_inv(12)) << (15-12)*4) + (block_state(state_permutation_inv(13)) << (15-13)*4) + (block_state(state_permutation_inv(14)) << (15-14)*4) + (block_state(state_permutation_inv(15)) << (15-15)*4)


        io.permuted_state := return_value
    }
    .otherwise
    {
        for (j <- 0 to 15) //hex to block
        {
            block_state(15-j) := ((io.state & ("hF".U << j*4)) >> j*4)
        }
    
        return_value := 0.U(64.W)

        return_value := (block_state(state_permutation(0)) << (15-0)*4) + (block_state(state_permutation(1)) << (15-1)*4) + (block_state(state_permutation(2)) << (15-2)*4) + (block_state(state_permutation(3)) << (15-3)*4) + (block_state(state_permutation(4)) << (15-4)*4) + (block_state(state_permutation(5)) << (15-5)*4) + (block_state(state_permutation(6)) << (15-6)*4) + (block_state(state_permutation(7)) << (15-7)*4) + (block_state(state_permutation(8)) << (15-8)*4) + (block_state(state_permutation(9)) << (15-9)*4) + (block_state(state_permutation(10)) << (15-10)*4) + (block_state(state_permutation(11)) << (15-11)*4) + (block_state(state_permutation(12)) << (15-12)*4) + (block_state(state_permutation(13)) << (15-13)*4) + (block_state(state_permutation(14)) << (15-14)*4) + (block_state(state_permutation(15)) << (15-15)*4)


        io.permuted_state := return_value
    }
}