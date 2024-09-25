#![feature(generators, generator_trait)]
use std::ops::{Generator, GeneratorState};

fn trampoline<Arg, Res, Gen>(f: impl Fn(Arg) -> Gen) -> impl Fn(Arg) -> Res
where
    Res: Default,
    Gen: Generator<Res, Yield=Arg, Return=Res> + Unpin,
{}
fn trianble_safe(n: u64) -> u64 {}