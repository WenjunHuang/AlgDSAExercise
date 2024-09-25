import qualified System.Exit as Exit
import Test.HUnit
import TypeInferenceTest

tests :: Test
tests = TestList [TestLabel "quantify" testQuantify]

main :: IO ()
main = do
  result <- runTestTT tests
  if failures result > 0 then Exit.exitFailure else Exit.exitSuccess
