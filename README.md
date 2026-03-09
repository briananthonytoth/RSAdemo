# RSA Cryptography Demo

A Java implementation of some cryptographic tasks related to RSA that I wrote for EECS 4670 a few years ago.

## Tasks

### 1. RSA Decryption
Performs basic RSA decryption: given a ciphertext *c*, private key *d*, and modulus *N*, recovers the plaintext as *m = c^d mod N*. This is the fundamental operation underlying all RSA-based secure communication.

### 2. Dictionary Attack on a Salted Password Hash
Given a SHA-256 hash of an unknown `password + salt` string, brute-forces all combinations from a list of 1,000 common passwords (used as both the password list and the salt list), yielding 1,000,000 candidates. Demonstrates why password storage requires both salting and a slow hashing algorithm; SHA-256 alone is insufficient.

### 3. Factoring a Weak Modulus and Private Key Recovery
Given a 64-bit RSA modulus *N*, finds its two prime factors by trial division starting from √N. Once *p* and *q* are known, the private key is derived as *d = e⁻¹ mod φ(N)*. Illustrates why RSA moduli must be at least 2048 bits: a tiny modulus, like this one, can be brute-forced quickly enough.

### 4. Håstad's Broadcast Attack
Recovers a plaintext message that was encrypted with a low public key (*e* = 3) and sent to three different recipients. Using the Chinese Remainder Theorem, the three ciphertexts are combined to reconstruct *m³*, from which the integer cube root yields *m* directly — no private key required. This attack is why RSA in practice always uses padding (e.g. OAEP) rather than raw textbook encryption.

## Requirements

* Java 9 or later (JDK; `javac` and `jar` are required to build)
* `javac` and `jar` on your system PATH

## Building and Running

### Windows

Run `build.bat` to compile the source and produce `Lab3.jar`, then `run.bat` to execute it. Both can be run by double-clicking or from the command prompt:

```
build.bat
run.bat
```

### Unix / macOS

```
make run
```

Individual targets:

| Target | Action |
|---|---|
| `make` | Compile source to `out/` |
| `make package` | Compile and produce `Lab3.jar` |
| `make run` | Compile, package, and run |
| `make clean` | Remove `out/` and `Lab3.jar` |

## Notes

* `Top_Passwords.txt` must be present in the working directory (the project root) at runtime; it is read in Task 2.
* The module name `Lab3` produces a compiler warning about terminal digits; this is harmless.
* All cryptographic values are hardcoded as they were provided in the original lab handout.