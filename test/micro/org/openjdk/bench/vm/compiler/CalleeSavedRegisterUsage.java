/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2025, BELLSOFT. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.bench.vm.compiler;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Tests the effect of using callee-saved registers to store temporary values
 * between method calls. Currently, the calling convention allows only a single
 * callee-saved register, the frame pointer, to be used by HotSpot.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 4, warmups = 1)
public class CalleeSavedRegisterUsage {

    @Param("100")
    private int ITERS;

    private int i;

    @Setup
    public void Setup() {
        i = 42;
    }

    @Benchmark
    @Fork(jvmArgs={"-XX:-TieredCompilation", "-Xbatch", "-Xcomp", "-XX:+PreserveFramePointer"})
    public void preserve_fp_baseline(Blackhole bh) {
        bh.consume(i);
    }

    @Benchmark
    @Fork(jvmArgs={"-XX:-TieredCompilation", "-Xbatch", "-Xcomp", "-XX:+PreserveFramePointer"})
    public void preserve_fp_singleShot(Blackhole bh) {
        singleShot(bh);
    }

    @Benchmark
    @Fork(jvmArgs={"-XX:-TieredCompilation", "-Xbatch", "-Xcomp", "-XX:+PreserveFramePointer"})
    public void preserve_fp_loop(Blackhole bh) {
        loop(bh);
    }

    @Benchmark
    @Fork(jvmArgs={"-XX:-TieredCompilation", "-Xbatch", "-Xcomp", "-XX:-PreserveFramePointer"})
    public void dont_preserve_fp_baseline(Blackhole bh) {
        bh.consume(i);
    }

    @Benchmark
    @Fork(jvmArgs={"-XX:-TieredCompilation", "-Xbatch", "-Xcomp", "-XX:-PreserveFramePointer"})
    public void dont_preserve_fp_singleShot(Blackhole bh) {
        singleShot(bh);
    }

    @Benchmark
    @Fork(jvmArgs={"-XX:-TieredCompilation", "-Xbatch", "-Xcomp", "-XX:-PreserveFramePointer"})
    public void dont_preserve_fp_loop(Blackhole bh) {
        loop(bh);
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    private void singleShot(Blackhole bh) {
        int res = sink(i);
        bh.consume(res + i);
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    private void loop(Blackhole bh) {
        for (int j = 0; j < ITERS; j++) {
            int arg = i + j;
            int res = sink(arg);
            bh.consume(res + arg);
        }
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private static int sink(int v) {
        return v;
    }
}
