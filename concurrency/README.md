### Concurrency with Futures

#### blocking.sc
Illustrates blocking and non-blocking implementations.

When run using ammonite the blocking version returns a String. But the script completes before the non-blocking version can run its Future in another thread.

```
concurrency$ amm blocking.sc
fetchUserNameBlocking: Mr Test1
fetchUserNameNonBlocking: Future(<not completed>)
```

#### flatMap.sc
Shows how Futures are eager - they can kick off the computation as soon as you create them. By creating them outside of the for/yield we can run them in parallel rather than in sequence.

```
concurrency$ amm -p flatMap.sc
Loading...
Welcome to the Ammonite Repl 2.2.0 (Scala 2.13.3 Java 11.0.10)
@ sequential()
a sleeping...
a awoke
b sleeping...
b awoke


@ parallel()
b sleeping...
a sleeping...
b awoke
a awoke
```