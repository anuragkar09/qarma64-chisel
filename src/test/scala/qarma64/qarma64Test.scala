import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
//import chisel3.iotesters
//import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

//Qarma64 basic tester
class BasicTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "QARMA64"
    it should "do something" in {
        test(new qarma64.QARMA64){c =>
            c.io.plaintext.poke(1.U)
            c.clock.step()
            c.io.cipher.expect(1.U)
            println("Last Output value :" + c.io.cipher.peek().litValue)
            
        }
    }
}

//tester for permute_tweak  
class PermuteTweakTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "PermuteTweak"
    it should "Permute the hex digits of a tweak" in {
        test(new qarma64.PermuteTweak){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.clock.step()
           // c.clock.step()
            c.io.permuted_tweak.expect("hc66cbe54f0c634e9".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)
            
        }
    }
}

//tester for permute_state
class PermuteStateTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "PermuteState"
    it should "Permute the hex digits of a state" in {
        test(new qarma64.PermuteState){c =>
            c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.inverse.poke(false.B)
            c.clock.step()
            c.io.permuted_state.expect("hb9ccee0f6643c645".U)

            c.io.state.poke("hBE5466CF34E90C6C".U)
            c.io.inverse.poke(true.B)
            c.clock.step()
            c.io.permuted_state.expect("hb6cec35f966ec440".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)
            
        }
    }
}

class TweakLFSRTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "TweakLFSR"
    it should "Do an LFSR operation on an input tweak" in {
        test(new qarma64.TweakLFSR){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            c.clock.step()
            c.clock.step()
            c.io.lfsr.expect("h5f52b6cf14ec066c".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)
            
        }
    }
}

/*class CalcTweakTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "CalcTweak"
    it should "Do r rounds of permute and LFSR" in {
        test(new CalcTweak(5)){c =>
            c.io.tweak.poke("hBE5466CF34E90C6C".U)
            //c.io.r.poke(5.U)
            c.clock.step()
            c.clock.step()
            c.clock.step()
            c.clock.step()
            c.clock.step()
            //c.clock.step()
            c.io.tweak_r.expect("h6a0a772dab3a691e".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)
            
        }
    }
}*/

class MixColumnsTest extends AnyFlatSpec with ChiselScalatestTester{
    behavior of "MixColumns Module"
    it should "Mix each column in a set manner" in {
        test(new qarma64.MixColumns){c =>
            c.io.state.poke("hbe5466cf34e90c6c".U)
            
            for (i <- 0 until 4) // 4 clock cycles
            {
               c.clock.step()
            }
            
            c.io.mixed_state.expect("h04e016e82e078c44".U)
            //println("Last Output value :" + c.io.cipher.peek().litValue)
            
        }
    }
}
