/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2025, BELLSOFT. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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

/*
 * @test
 * @bug 0000000
 * @summary Build a function frame on aarch64 using appropriate machine instructions regardless of the frame's size
 *
 * @requires vm.flagless
 * @requires os.arch=="aarch64"
 * @requires vm.compiler2.enabled
 * @requires vm.compiler1.enabled
 *
 * @run main/othervm -Xbatch -Xcomp -XX:TieredStopAtLevel=1 -XX:+TieredCompilation
 *                   -XX:CompileCommand=compileonly,*TestBuildCorrectFrame::test*
 *                   -XX:CompileCommand=dontinline,*TestBuildCorrectFrame::test* -XX:CompileCommand=quiet
 *                   -XX:-DontCompileHugeMethods -XX:MaxLabelRootDepth=2000
 *                   compiler.codegen.aarch64.TestBuildCorrectFrame
 * @run main/othervm -Xbatch -Xcomp -XX:-TieredCompilation
 *                   -XX:CompileCommand=compileonly,*TestBuildCorrectFrame::test*
 *                   -XX:CompileCommand=dontinline,*TestBuildCorrectFrame::test* -XX:CompileCommand=quiet
 *                   -XX:-DontCompileHugeMethods -XX:MaxLabelRootDepth=2000
 *                   compiler.codegen.aarch64.TestBuildCorrectFrame
 */

package compiler.codegen.aarch64;

public class TestBuildCorrectFrame {

    static int[] arr0 = new int[128];
    static int[] arr1 = new int[128];
    static int[] arr2 = new int[128];
    static int[] arr3 = new int[128];
    static int[] arr4 = new int[128];
    static int[] arr5 = new int[128];
    static int[] arr6 = new int[128];
    static int[] arr7 = new int[128];
    static int[] arr8 = new int[128];

    /**
     * Very small frame: 0 < frame size < 528 ((1 << 9) + 2 * wordSize), wordSize == 2 on aarch64.
     * The generated code for function prologues and epilogues is the following:
     * sub             sp, sp, #0x40
     * stp             x29, x30, [sp, #0x30]
     * ...
     * ldp             x29, x30, [sp, #0x30]
     * add             sp, sp, #0x40
     */
    static long testTinyFrame() {
        long x = arr0[0], y = arr0[1];
        return x + y;
    }

    /**
     * Small frame: 0 < frame size < 528 ((1 << 9) + 2 * wordSize), wordSize == 2 on aarch64.
     * The generated code for function prologues and epilogues looks as the following:
     * sub             sp, sp, #0x200
     * stp             x29, x30, [sp, #0x1F0]
     * ...
     * ldp             x29, x30, [sp, #0x1F0]
     * add             sp, sp, #0x200
     */
    static int testSmallFrame() {
        int x0 = arr0[0], x1 = arr0[1], x2 = arr0[2], x3 = arr0[3], x4 = arr0[4], x5 = arr0[5], x6 = arr0[6],
            x7 = arr0[7], x8 = arr0[8], x9 = arr0[9], x10 = arr0[10], x11 = arr0[11], x12 = arr0[12],
            x13 = arr0[13], x14 = arr0[14], x15 = arr0[15];
        int y0 = arr1[0], y1 = arr1[1], y2 = arr1[2], y3 = arr1[3], y4 = arr1[4], y5 = arr1[5], y6 = arr1[6],
            y7 = arr1[7], y8 = arr1[8], y9 = arr1[9], y10 = arr1[10], y11 = arr1[11], y12 = arr1[12],
            y13 = arr1[13], y14 = arr1[14], y15 = arr1[15];
        int z0 = arr2[0], z1 = arr2[1], z2 = arr2[2], z3 = arr2[3], z4 = arr2[4], z5 = arr2[5], z6 = arr2[6],
            z7 = arr2[7], z8 = arr2[8], z9 = arr2[9], z10 = arr2[10], z11 = arr2[11], z12 = arr2[12],
            z13 = arr2[13], z14 = arr2[14], z15 = arr2[15];
        int w0 = arr3[0], w1 = arr3[1], w2 = arr3[2], w3 = arr3[3], w4 = arr3[4], w5 = arr3[5], w6 = arr3[6],
            w7 = arr3[7], w8 = arr3[8], w9 = arr3[9], w10 = arr3[10], w11 = arr3[11], w12 = arr3[12],
            w13 = arr3[13], w14 = arr3[14], w15 = arr3[15];
        int v0 = arr4[0], v1 = arr4[1], v2 = arr4[2], v3 = arr4[3], v4 = arr4[4], v5 = arr4[5], v6 = arr4[6],
            v7 = arr4[7], v8 = arr4[8], v9 = arr4[9], v10 = arr4[10], v11 = arr4[11], v12 = arr4[12],
            v13 = arr4[13], v14 = arr4[14], v15 = arr4[15];
        int u0 = arr5[0], u1 = arr5[1], u2 = arr5[2], u3 = arr5[3], u4 = arr5[4], u5 = arr5[5], u6 = arr5[6],
            u7 = arr5[7], u8 = arr5[8], u9 = arr5[9], u10 = arr5[10], u11 = arr5[11], u12 = arr5[12],
            u13 = arr5[13], u14 = arr5[14], u15 = arr5[15];
        int t0 = arr6[0], t1 = arr6[1], t2 = arr6[2], t3 = arr6[3], t4 = arr6[4], t5 = arr6[5], t6 = arr6[6],
            t7 = arr6[7], t8 = arr6[8], t9 = arr6[9], t10 = arr6[10], t11 = arr6[11], t12 = arr6[12],
            t13 = arr6[13], t14 = arr6[14], t15 = arr6[15];
        int s0 = arr7[0], s1 = arr7[1], s2 = arr7[2], s3 = arr7[3], s4 = arr7[4], s5 = arr7[5], s6 = arr7[6],
            s7 = arr7[7], s8 = arr7[8], s9 = arr7[9], s10 = arr7[10], s11 = arr7[11], s12 = arr7[12],
            s13 = arr7[13], s14 = arr7[14], s15 = arr7[15];

        int k0  = x0  + y0  * z0  - w0  - v0  + u0  * t0  - s0,
            k1  = x1  + y1  * z1  + w1  - v1  + u1  * t1  - s1,
            k2  = x2  - y2  * z2  - w2  - v2  + u2  * t2  - s2,
            k3  = x3  - y3  * z3  + w3  - v3  + u3  * t3  - s3,
            k4  = x4  + y4  * z4  - w4  - v4  - u4  * t4  - s4,
            k5  = x5  + y5  * z5  + w5  - v5  - u5  * t5  - s5,
            k6  = x6  - y6  * z6  - w6  - v6  - u6  * t6  - s6,
            k7  = x7  - y7  * z7  + w7  - v7  - u7  * t7  - s7,
            k8  = x8  + y8  * z8  - w8  - v8  + u8  * t8  - s8,
            k9  = x9  + y9  * z9  + w9  - v9  + u9  * t9  - s9,
            k10 = x10 - y10 * z10 - w10 - v10 + u10 * t10 - s10,
            k11 = x11 - y11 * z11 + w11 - v11 + u11 * t11 - s11,
            k12 = x12 + y12 * z12 - w12 - v12 - u12 * t12 - s12,
            k13 = x13 + y13 * z13 + w13 - v13 - u13 * t13 - s13,
            k14 = x14 - y14 * z14 - w14 - v14 - u14 * t14 - s14,
            k15 = x15 - y15 * z15 + w15 - v15 - u15 * t15 - s15;

        return k0 + k1 + k2 + k3 + k4 + k5 + k6 + k7 + k8 + k9 + k10 + k11 + k12 + k13 + k14 + k15;
    }

    /**
     * Medium frame: 528 ((1 << 9) + 2 * wordSize) <= frame size < 4112 ((1 << 12) + 2 * wordSize),
     * wordSize == 2 on aarch64.
     * The generated code for function prologues and epilogues looks as the following:
     * stp             x29, x30, [sp, #-0x10]!
     * sub             sp, sp, #0x200
     * ...
     * add             sp, sp, #0x200
     * ldp             x29, x30, [sp], #0x10
     */
    static int testMediumFrame() {
        int x0 = arr0[0], x1 = arr0[1], x2 = arr0[2], x3 = arr0[3], x4 = arr0[4], x5 = arr0[5], x6 = arr0[6],
            x7 = arr0[7], x8 = arr0[8], x9 = arr0[9], x10 = arr0[10], x11 = arr0[11], x12 = arr0[12],
            x13 = arr0[13], x14 = arr0[14], x15 = arr0[15], x16 = arr0[16], x17 = arr0[17], x18 = arr0[18],
            x19 = arr0[19], x20 = arr0[20], x21 = arr0[21], x22 = arr0[22], x23 = arr0[23], x24 = arr0[24],
            x25 = arr0[25], x26 = arr0[26], x27 = arr0[27], x28 = arr0[28], x29 = arr0[29], x30 = arr0[30],
            x31 = arr0[31];
        int y0 = arr1[0], y1 = arr1[1], y2 = arr1[2], y3 = arr1[3], y4 = arr1[4], y5 = arr1[5], y6 = arr1[6],
            y7 = arr1[7], y8 = arr1[8], y9 = arr1[9], y10 = arr1[10], y11 = arr1[11], y12 = arr1[12],
            y13 = arr1[13], y14 = arr1[14], y15 = arr1[15], y16 = arr1[16], y17 = arr1[17], y18 = arr1[18],
            y19 = arr1[19], y20 = arr1[20], y21 = arr1[21], y22 = arr1[22], y23 = arr1[23], y24 = arr1[24],
            y25 = arr1[25], y26 = arr1[26], y27 = arr1[27], y28 = arr1[28], y29 = arr1[29], y30 = arr1[30],
            y31 = arr1[31];
        int z0 = arr2[0], z1 = arr2[1], z2 = arr2[2], z3 = arr2[3], z4 = arr2[4], z5 = arr2[5], z6 = arr2[6],
            z7 = arr2[7], z8 = arr2[8], z9 = arr2[9], z10 = arr2[10], z11 = arr2[11], z12 = arr2[12],
            z13 = arr2[13], z14 = arr2[14], z15 = arr2[15], z16 = arr2[16], z17 = arr2[17], z18 = arr2[18],
            z19 = arr2[19], z20 = arr2[20], z21 = arr2[21], z22 = arr2[22], z23 = arr2[23], z24 = arr2[24],
            z25 = arr2[25], z26 = arr2[26], z27 = arr2[27], z28 = arr2[28], z29 = arr2[29], z30 = arr2[30],
            z31 = arr2[31];
        int w0 = arr3[0], w1 = arr3[1], w2 = arr3[2], w3 = arr3[3], w4 = arr3[4], w5 = arr3[5], w6 = arr3[6],
            w7 = arr3[7], w8 = arr3[8], w9 = arr3[9], w10 = arr3[10], w11 = arr3[11], w12 = arr3[12],
            w13 = arr3[13], w14 = arr3[14], w15 = arr3[15], w16 = arr3[16], w17 = arr3[17], w18 = arr3[18],
            w19 = arr3[19], w20 = arr3[20], w21 = arr3[21], w22 = arr3[22], w23 = arr3[23], w24 = arr3[24],
            w25 = arr3[25], w26 = arr3[26], w27 = arr3[27], w28 = arr3[28], w29 = arr3[29], w30 = arr3[30],
            w31 = arr3[31];
        int v0 = arr4[0], v1 = arr4[1], v2 = arr4[2], v3 = arr4[3], v4 = arr4[4], v5 = arr4[5], v6 = arr4[6],
            v7 = arr4[7], v8 = arr4[8], v9 = arr4[9], v10 = arr4[10], v11 = arr4[11], v12 = arr4[12],
            v13 = arr4[13], v14 = arr4[14], v15 = arr4[15], v16 = arr4[16], v17 = arr4[17], v18 = arr4[18],
            v19 = arr4[19], v20 = arr4[20], v21 = arr4[21], v22 = arr4[22], v23 = arr4[23], v24 = arr4[24],
            v25 = arr4[25], v26 = arr4[26], v27 = arr4[27], v28 = arr4[28], v29 = arr4[29], v30 = arr4[30],
            v31 = arr4[31];
        int u0 = arr5[0], u1 = arr5[1], u2 = arr5[2], u3 = arr5[3], u4 = arr5[4], u5 = arr5[5], u6 = arr5[6],
            u7 = arr5[7], u8 = arr5[8], u9 = arr5[9], u10 = arr5[10], u11 = arr5[11], u12 = arr5[12],
            u13 = arr5[13], u14 = arr5[14], u15 = arr5[15], u16 = arr5[16], u17 = arr5[17], u18 = arr5[18],
            u19 = arr5[19], u20 = arr5[20], u21 = arr5[21], u22 = arr5[22], u23 = arr5[23], u24 = arr5[24],
            u25 = arr5[25], u26 = arr5[26], u27 = arr5[27], u28 = arr5[28], u29 = arr5[29], u30 = arr5[30],
            u31 = arr5[31];
        int t0 = arr6[0], t1 = arr6[1], t2 = arr6[2], t3 = arr6[3], t4 = arr6[4], t5 = arr6[5], t6 = arr6[6],
            t7 = arr6[7], t8 = arr6[8], t9 = arr6[9], t10 = arr6[10], t11 = arr6[11], t12 = arr6[12],
            t13 = arr6[13], t14 = arr6[14], t15 = arr6[15], t16 = arr6[16], t17 = arr6[17], t18 = arr6[18],
            t19 = arr6[19], t20 = arr6[20], t21 = arr6[21], t22 = arr6[22], t23 = arr6[23], t24 = arr6[24],
            t25 = arr6[25], t26 = arr6[26], t27 = arr6[27], t28 = arr6[28], t29 = arr6[29], t30 = arr6[30],
            t31 = arr6[31];
        int s0 = arr7[0], s1 = arr7[1], s2 = arr7[2], s3 = arr7[3], s4 = arr7[4], s5 = arr7[5], s6 = arr7[6],
            s7 = arr7[7], s8 = arr7[8], s9 = arr7[9], s10 = arr7[10], s11 = arr7[11], s12 = arr7[12],
            s13 = arr7[13], s14 = arr7[14], s15 = arr7[15], s16 = arr7[16], s17 = arr7[17], s18 = arr7[18],
            s19 = arr7[19], s20 = arr7[20], s21 = arr7[21], s22 = arr7[22], s23 = arr7[23], s24 = arr7[24],
            s25 = arr7[25], s26 = arr7[26], s27 = arr7[27], s28 = arr7[28], s29 = arr7[29], s30 = arr7[30],
            s31 = arr7[31];

        int k0  = x0  + y0  * z0  - w0  - v0  + u0  * t0  - s0,
            k1  = x1  + y1  * z1  + w1  - v1  + u1  * t1  - s1,
            k2  = x2  - y2  * z2  - w2  - v2  + u2  * t2  - s2,
            k3  = x3  - y3  * z3  + w3  - v3  + u3  * t3  - s3,
            k4  = x4  + y4  * z4  - w4  - v4  - u4  * t4  - s4,
            k5  = x5  + y5  * z5  + w5  - v5  - u5  * t5  - s5,
            k6  = x6  - y6  * z6  - w6  - v6  - u6  * t6  - s6,
            k7  = x7  - y7  * z7  + w7  - v7  - u7  * t7  - s7,
            k8  = x8  + y8  * z8  - w8  - v8  + u8  * t8  - s8,
            k9  = x9  + y9  * z9  + w9  - v9  + u9  * t9  - s9,
            k10 = x10 - y10 * z10 - w10 - v10 + u10 * t10 - s10,
            k11 = x11 - y11 * z11 + w11 - v11 + u11 * t11 - s11,
            k12 = x12 + y12 * z12 - w12 - v12 - u12 * t12 - s12,
            k13 = x13 + y13 * z13 + w13 - v13 - u13 * t13 - s13,
            k14 = x14 - y14 * z14 - w14 - v14 - u14 * t14 - s14,
            k15 = x15 - y15 * z15 + w15 - v15 - u15 * t15 - s15,
            k16 = x16 + y16 * z16 - w16 - v16 + u16 * t16 - s16,
            k17 = x17 + y17 * z17 + w17 - v17 + u17 * t17 - s17,
            k18 = x18 - y18 * z18 - w18 - v18 + u18 * t18 - s18,
            k19 = x19 - y19 * z19 + w19 - v19 + u19 * t19 - s19,
            k20 = x20 + y20 * z20 - w20 - v20 - u20 * t20 - s20,
            k21 = x21 + y21 * z21 + w21 - v21 - u21 * t21 - s21,
            k22 = x22 - y22 * z22 - w22 - v22 - u22 * t22 - s22,
            k23 = x23 - y23 * z23 + w23 - v23 - u23 * t23 - s23,
            k24 = x24 + y24 * z24 - w24 - v24 + u24 * t24 - s24,
            k25 = x25 + y25 * z25 + w25 - v25 + u25 * t25 - s25,
            k26 = x26 - y26 * z26 - w26 - v26 + u26 * t26 - s26,
            k27 = x27 - y27 * z27 + w27 - v27 + u27 * t27 - s27,
            k28 = x28 + y28 * z28 - w28 - v28 - u28 * t28 - s28,
            k29 = x29 + y29 * z29 + w29 - v29 - u29 * t29 - s29,
            k30 = x30 - y30 * z30 - w30 - v30 - u30 * t30 - s30,
            k31 = x31 - y31 * z31 + w31 - v31 - u31 * t31 - s31;

        return k0 + k1 + k2 + k3 + k4 + k5 + k6 + k7 + k8 + k9 + k10 + k11 + k12 + k13 + k14 + k15 +
               k16 + k17 + k18 + k19 + k20 + k21 + k22 + k23 + k24 + k25 + k26 + k27 + k28 + k29 + k30 + k31;
    }

    /**
     * Large frame: frame size >= 4112 ((1 << 12) + 2 * wordSize), wordSize == 2 on aarch64.
     * The generated code for function prologues and epilogues looks as the following:
     * stp             x29, x30, [sp, #-0x10]!
     * mov             x8, #0x1020
     * sub             sp, sp, x8
     * ...
     * mov             x8, #0x1020
     * add             sp, sp, x8
     * ldp             x29, x30, [sp], #0x10
     */
    static int testLargeFrame() {
        int x0 = arr0[0], x1 = arr0[1], x2 = arr0[2], x3 = arr0[3], x4 = arr0[4], x5 = arr0[5], x6 = arr0[6],
            x7 = arr0[7], x8 = arr0[8], x9 = arr0[9], x10 = arr0[10], x11 = arr0[11], x12 = arr0[12],
            x13 = arr0[13], x14 = arr0[14], x15 = arr0[15], x16 = arr0[16], x17 = arr0[17], x18 = arr0[18],
            x19 = arr0[19], x20 = arr0[20], x21 = arr0[21], x22 = arr0[22], x23 = arr0[23], x24 = arr0[24],
            x25 = arr0[25], x26 = arr0[26], x27 = arr0[27], x28 = arr0[28], x29 = arr0[29], x30 = arr0[30],
            x31 = arr0[31], x32 = arr0[32], x33 = arr0[33], x34 = arr0[34], x35 = arr0[35], x36 = arr0[36],
            x37 = arr0[37], x38 = arr0[38], x39 = arr0[39], x40 = arr0[40], x41 = arr0[41], x42 = arr0[42],
            x43 = arr0[43], x44 = arr0[44], x45 = arr0[45], x46 = arr0[46], x47 = arr0[47], x48 = arr0[48],
            x49 = arr0[49], x50 = arr0[50], x51 = arr0[51], x52 = arr0[52], x53 = arr0[53], x54 = arr0[54],
            x55 = arr0[55], x56 = arr0[56], x57 = arr0[57], x58 = arr0[58], x59 = arr0[59], x60 = arr0[60],
            x61 = arr0[61], x62 = arr0[62], x63 = arr0[63], x64 = arr0[64], x65 = arr0[65], x66 = arr0[66],
            x67 = arr0[67], x68 = arr0[68], x69 = arr0[69], x70 = arr0[70], x71 = arr0[71], x72 = arr0[72],
            x73 = arr0[73], x74 = arr0[74], x75 = arr0[75], x76 = arr0[76], x77 = arr0[77], x78 = arr0[78],
            x79 = arr0[79], x80 = arr0[80], x81 = arr0[81], x82 = arr0[82], x83 = arr0[83], x84 = arr0[84],
            x85 = arr0[85], x86 = arr0[86], x87 = arr0[87], x88 = arr0[88], x89 = arr0[89], x90 = arr0[90],
            x91 = arr0[91], x92 = arr0[92], x93 = arr0[93], x94 = arr0[94], x95 = arr0[95], x96 = arr0[96],
            x97 = arr0[97], x98 = arr0[98], x99 = arr0[99], x100 = arr0[100], x101 = arr0[101], x102 = arr0[102],
            x103 = arr0[103], x104 = arr0[104], x105 = arr0[105], x106 = arr0[106], x107 = arr0[107],
            x108 = arr0[108], x109 = arr0[109], x110 = arr0[110], x111 = arr0[111], x112 = arr0[112],
            x113 = arr0[113], x114 = arr0[114], x115 = arr0[115], x116 = arr0[116], x117 = arr0[117],
            x118 = arr0[118], x119 = arr0[119], x120 = arr0[120], x121 = arr0[121], x122 = arr0[122],
            x123 = arr0[123], x124 = arr0[124], x125 = arr0[125], x126 = arr0[126], x127 = arr0[127];
        int y0 = arr1[0], y1 = arr1[1], y2 = arr1[2], y3 = arr1[3], y4 = arr1[4], y5 = arr1[5], y6 = arr1[6],
            y7 = arr1[7], y8 = arr1[8], y9 = arr1[9], y10 = arr1[10], y11 = arr1[11], y12 = arr1[12],
            y13 = arr1[13], y14 = arr1[14], y15 = arr1[15], y16 = arr1[16], y17 = arr1[17], y18 = arr1[18],
            y19 = arr1[19], y20 = arr1[20], y21 = arr1[21], y22 = arr1[22], y23 = arr1[23], y24 = arr1[24],
            y25 = arr1[25], y26 = arr1[26], y27 = arr1[27], y28 = arr1[28], y29 = arr1[29], y30 = arr1[30],
            y31 = arr1[31], y32 = arr1[32], y33 = arr1[33], y34 = arr1[34], y35 = arr1[35], y36 = arr1[36],
            y37 = arr1[37], y38 = arr1[38], y39 = arr1[39], y40 = arr1[40], y41 = arr1[41], y42 = arr1[42],
            y43 = arr1[43], y44 = arr1[44], y45 = arr1[45], y46 = arr1[46], y47 = arr1[47], y48 = arr1[48],
            y49 = arr1[49], y50 = arr1[50], y51 = arr1[51], y52 = arr1[52], y53 = arr1[53], y54 = arr1[54],
            y55 = arr1[55], y56 = arr1[56], y57 = arr1[57], y58 = arr1[58], y59 = arr1[59], y60 = arr1[60],
            y61 = arr1[61], y62 = arr1[62], y63 = arr1[63], y64 = arr1[64], y65 = arr1[65], y66 = arr1[66],
            y67 = arr1[67], y68 = arr1[68], y69 = arr1[69], y70 = arr1[70], y71 = arr1[71], y72 = arr1[72],
            y73 = arr1[73], y74 = arr1[74], y75 = arr1[75], y76 = arr1[76], y77 = arr1[77], y78 = arr1[78],
            y79 = arr1[79], y80 = arr1[80], y81 = arr1[81], y82 = arr1[82], y83 = arr1[83], y84 = arr1[84],
            y85 = arr1[85], y86 = arr1[86], y87 = arr1[87], y88 = arr1[88], y89 = arr1[89], y90 = arr1[90],
            y91 = arr1[91], y92 = arr1[92], y93 = arr1[93], y94 = arr1[94], y95 = arr1[95], y96 = arr1[96],
            y97 = arr1[97], y98 = arr1[98], y99 = arr1[99], y100 = arr1[100], y101 = arr1[101], y102 = arr1[102],
            y103 = arr1[103], y104 = arr1[104], y105 = arr1[105], y106 = arr1[106], y107 = arr1[107],
            y108 = arr1[108], y109 = arr1[109], y110 = arr1[110], y111 = arr1[111], y112 = arr1[112],
            y113 = arr1[113], y114 = arr1[114], y115 = arr1[115], y116 = arr1[116], y117 = arr1[117],
            y118 = arr1[118], y119 = arr1[119], y120 = arr1[120], y121 = arr1[121], y122 = arr1[122],
            y123 = arr1[123], y124 = arr1[124], y125 = arr1[125], y126 = arr1[126], y127 = arr1[127];
        int z0 = arr2[0], z1 = arr2[1], z2 = arr2[2], z3 = arr2[3], z4 = arr2[4], z5 = arr2[5], z6 = arr2[6],
            z7 = arr2[7], z8 = arr2[8], z9 = arr2[9], z10 = arr2[10], z11 = arr2[11], z12 = arr2[12],
            z13 = arr2[13], z14 = arr2[14], z15 = arr2[15], z16 = arr2[16], z17 = arr2[17], z18 = arr2[18],
            z19 = arr2[19], z20 = arr2[20], z21 = arr2[21], z22 = arr2[22], z23 = arr2[23], z24 = arr2[24],
            z25 = arr2[25], z26 = arr2[26], z27 = arr2[27], z28 = arr2[28], z29 = arr2[29], z30 = arr2[30],
            z31 = arr2[31], z32 = arr2[32], z33 = arr2[33], z34 = arr2[34], z35 = arr2[35], z36 = arr2[36],
            z37 = arr2[37], z38 = arr2[38], z39 = arr2[39], z40 = arr2[40], z41 = arr2[41], z42 = arr2[42],
            z43 = arr2[43], z44 = arr2[44], z45 = arr2[45], z46 = arr2[46], z47 = arr2[47], z48 = arr2[48],
            z49 = arr2[49], z50 = arr2[50], z51 = arr2[51], z52 = arr2[52], z53 = arr2[53], z54 = arr2[54],
            z55 = arr2[55], z56 = arr2[56], z57 = arr2[57], z58 = arr2[58], z59 = arr2[59], z60 = arr2[60],
            z61 = arr2[61], z62 = arr2[62], z63 = arr2[63], z64 = arr2[64], z65 = arr2[65], z66 = arr2[66],
            z67 = arr2[67], z68 = arr2[68], z69 = arr2[69], z70 = arr2[70], z71 = arr2[71], z72 = arr2[72],
            z73 = arr2[73], z74 = arr2[74], z75 = arr2[75], z76 = arr2[76], z77 = arr2[77], z78 = arr2[78],
            z79 = arr2[79], z80 = arr2[80], z81 = arr2[81], z82 = arr2[82], z83 = arr2[83], z84 = arr2[84],
            z85 = arr2[85], z86 = arr2[86], z87 = arr2[87], z88 = arr2[88], z89 = arr2[89], z90 = arr2[90],
            z91 = arr2[91], z92 = arr2[92], z93 = arr2[93], z94 = arr2[94], z95 = arr2[95], z96 = arr2[96],
            z97 = arr2[97], z98 = arr2[98], z99 = arr2[99], z100 = arr2[100], z101 = arr2[101], z102 = arr2[102],
            z103 = arr2[103], z104 = arr2[104], z105 = arr2[105], z106 = arr2[106], z107 = arr2[107],
            z108 = arr2[108], z109 = arr2[109], z110 = arr2[110], z111 = arr2[111], z112 = arr2[112],
            z113 = arr2[113], z114 = arr2[114], z115 = arr2[115], z116 = arr2[116], z117 = arr2[117],
            z118 = arr2[118], z119 = arr2[119], z120 = arr2[120], z121 = arr2[121], z122 = arr2[122],
            z123 = arr2[123], z124 = arr2[124], z125 = arr2[125], z126 = arr2[126], z127 = arr2[127];
        int w0 = arr3[0], w1 = arr3[1], w2 = arr3[2], w3 = arr3[3], w4 = arr3[4], w5 = arr3[5], w6 = arr3[6],
            w7 = arr3[7], w8 = arr3[8], w9 = arr3[9], w10 = arr3[10], w11 = arr3[11], w12 = arr3[12],
            w13 = arr3[13], w14 = arr3[14], w15 = arr3[15], w16 = arr3[16], w17 = arr3[17], w18 = arr3[18],
            w19 = arr3[19], w20 = arr3[20], w21 = arr3[21], w22 = arr3[22], w23 = arr3[23], w24 = arr3[24],
            w25 = arr3[25], w26 = arr3[26], w27 = arr3[27], w28 = arr3[28], w29 = arr3[29], w30 = arr3[30],
            w31 = arr3[31], w32 = arr3[32], w33 = arr3[33], w34 = arr3[34], w35 = arr3[35], w36 = arr3[36],
            w37 = arr3[37], w38 = arr3[38], w39 = arr3[39], w40 = arr3[40], w41 = arr3[41], w42 = arr3[42],
            w43 = arr3[43], w44 = arr3[44], w45 = arr3[45], w46 = arr3[46], w47 = arr3[47], w48 = arr3[48],
            w49 = arr3[49], w50 = arr3[50], w51 = arr3[51], w52 = arr3[52], w53 = arr3[53], w54 = arr3[54],
            w55 = arr3[55], w56 = arr3[56], w57 = arr3[57], w58 = arr3[58], w59 = arr3[59], w60 = arr3[60],
            w61 = arr3[61], w62 = arr3[62], w63 = arr3[63], w64 = arr3[64], w65 = arr3[65], w66 = arr3[66],
            w67 = arr3[67], w68 = arr3[68], w69 = arr3[69], w70 = arr3[70], w71 = arr3[71], w72 = arr3[72],
            w73 = arr3[73], w74 = arr3[74], w75 = arr3[75], w76 = arr3[76], w77 = arr3[77], w78 = arr3[78],
            w79 = arr3[79], w80 = arr3[80], w81 = arr3[81], w82 = arr3[82], w83 = arr3[83], w84 = arr3[84],
            w85 = arr3[85], w86 = arr3[86], w87 = arr3[87], w88 = arr3[88], w89 = arr3[89], w90 = arr3[90],
            w91 = arr3[91], w92 = arr3[92], w93 = arr3[93], w94 = arr3[94], w95 = arr3[95], w96 = arr3[96],
            w97 = arr3[97], w98 = arr3[98], w99 = arr3[99], w100 = arr3[100], w101 = arr3[101], w102 = arr3[102],
            w103 = arr3[103], w104 = arr3[104], w105 = arr3[105], w106 = arr3[106], w107 = arr3[107],
            w108 = arr3[108], w109 = arr3[109], w110 = arr3[110], w111 = arr3[111], w112 = arr3[112],
            w113 = arr3[113], w114 = arr3[114], w115 = arr3[115], w116 = arr3[116], w117 = arr3[117],
            w118 = arr3[118], w119 = arr3[119], w120 = arr3[120], w121 = arr3[121], w122 = arr3[122],
            w123 = arr3[123], w124 = arr3[124], w125 = arr3[125], w126 = arr3[126], w127 = arr3[127];
        int v0 = arr4[0], v1 = arr4[1], v2 = arr4[2], v3 = arr4[3], v4 = arr4[4], v5 = arr4[5], v6 = arr4[6],
            v7 = arr4[7], v8 = arr4[8], v9 = arr4[9], v10 = arr4[10], v11 = arr4[11], v12 = arr4[12],
            v13 = arr4[13], v14 = arr4[14], v15 = arr4[15], v16 = arr4[16], v17 = arr4[17], v18 = arr4[18],
            v19 = arr4[19], v20 = arr4[20], v21 = arr4[21], v22 = arr4[22], v23 = arr4[23], v24 = arr4[24],
            v25 = arr4[25], v26 = arr4[26], v27 = arr4[27], v28 = arr4[28], v29 = arr4[29], v30 = arr4[30],
            v31 = arr4[31], v32 = arr4[32], v33 = arr4[33], v34 = arr4[34], v35 = arr4[35], v36 = arr4[36],
            v37 = arr4[37], v38 = arr4[38], v39 = arr4[39], v40 = arr4[40], v41 = arr4[41], v42 = arr4[42],
            v43 = arr4[43], v44 = arr4[44], v45 = arr4[45], v46 = arr4[46], v47 = arr4[47], v48 = arr4[48],
            v49 = arr4[49], v50 = arr4[50], v51 = arr4[51], v52 = arr4[52], v53 = arr4[53], v54 = arr4[54],
            v55 = arr4[55], v56 = arr4[56], v57 = arr4[57], v58 = arr4[58], v59 = arr4[59], v60 = arr4[60],
            v61 = arr4[61], v62 = arr4[62], v63 = arr4[63], v64 = arr4[64], v65 = arr4[65], v66 = arr4[66],
            v67 = arr4[67], v68 = arr4[68], v69 = arr4[69], v70 = arr4[70], v71 = arr4[71], v72 = arr4[72],
            v73 = arr4[73], v74 = arr4[74], v75 = arr4[75], v76 = arr4[76], v77 = arr4[77], v78 = arr4[78],
            v79 = arr4[79], v80 = arr4[80], v81 = arr4[81], v82 = arr4[82], v83 = arr4[83], v84 = arr4[84],
            v85 = arr4[85], v86 = arr4[86], v87 = arr4[87], v88 = arr4[88], v89 = arr4[89], v90 = arr4[90],
            v91 = arr4[91], v92 = arr4[92], v93 = arr4[93], v94 = arr4[94], v95 = arr4[95], v96 = arr4[96],
            v97 = arr4[97], v98 = arr4[98], v99 = arr4[99], v100 = arr4[100], v101 = arr4[101], v102 = arr4[102],
            v103 = arr4[103], v104 = arr4[104], v105 = arr4[105], v106 = arr4[106], v107 = arr4[107],
            v108 = arr4[108], v109 = arr4[109], v110 = arr4[110], v111 = arr4[111], v112 = arr4[112],
            v113 = arr4[113], v114 = arr4[114], v115 = arr4[115], v116 = arr4[116], v117 = arr4[117],
            v118 = arr4[118], v119 = arr4[119], v120 = arr4[120], v121 = arr4[121], v122 = arr4[122],
            v123 = arr4[123], v124 = arr4[124], v125 = arr4[125], v126 = arr4[126], v127 = arr4[127];
        int u0 = arr5[0], u1 = arr5[1], u2 = arr5[2], u3 = arr5[3], u4 = arr5[4], u5 = arr5[5], u6 = arr5[6],
            u7 = arr5[7], u8 = arr5[8], u9 = arr5[9], u10 = arr5[10], u11 = arr5[11], u12 = arr5[12],
            u13 = arr5[13], u14 = arr5[14], u15 = arr5[15], u16 = arr5[16], u17 = arr5[17], u18 = arr5[18],
            u19 = arr5[19], u20 = arr5[20], u21 = arr5[21], u22 = arr5[22], u23 = arr5[23], u24 = arr5[24],
            u25 = arr5[25], u26 = arr5[26], u27 = arr5[27], u28 = arr5[28], u29 = arr5[29], u30 = arr5[30],
            u31 = arr5[31], u32 = arr5[32], u33 = arr5[33], u34 = arr5[34], u35 = arr5[35], u36 = arr5[36],
            u37 = arr5[37], u38 = arr5[38], u39 = arr5[39], u40 = arr5[40], u41 = arr5[41], u42 = arr5[42],
            u43 = arr5[43], u44 = arr5[44], u45 = arr5[45], u46 = arr5[46], u47 = arr5[47], u48 = arr5[48],
            u49 = arr5[49], u50 = arr5[50], u51 = arr5[51], u52 = arr5[52], u53 = arr5[53], u54 = arr5[54],
            u55 = arr5[55], u56 = arr5[56], u57 = arr5[57], u58 = arr5[58], u59 = arr5[59], u60 = arr5[60],
            u61 = arr5[61], u62 = arr5[62], u63 = arr5[63], u64 = arr5[64], u65 = arr5[65], u66 = arr5[66],
            u67 = arr5[67], u68 = arr5[68], u69 = arr5[69], u70 = arr5[70], u71 = arr5[71], u72 = arr5[72],
            u73 = arr5[73], u74 = arr5[74], u75 = arr5[75], u76 = arr5[76], u77 = arr5[77], u78 = arr5[78],
            u79 = arr5[79], u80 = arr5[80], u81 = arr5[81], u82 = arr5[82], u83 = arr5[83], u84 = arr5[84],
            u85 = arr5[85], u86 = arr5[86], u87 = arr5[87], u88 = arr5[88], u89 = arr5[89], u90 = arr5[90],
            u91 = arr5[91], u92 = arr5[92], u93 = arr5[93], u94 = arr5[94], u95 = arr5[95], u96 = arr5[96],
            u97 = arr5[97], u98 = arr5[98], u99 = arr5[99], u100 = arr5[100], u101 = arr5[101], u102 = arr5[102],
            u103 = arr5[103], u104 = arr5[104], u105 = arr5[105], u106 = arr5[106], u107 = arr5[107],
            u108 = arr5[108], u109 = arr5[109], u110 = arr5[110], u111 = arr5[111], u112 = arr5[112],
            u113 = arr5[113], u114 = arr5[114], u115 = arr5[115], u116 = arr5[116], u117 = arr5[117],
            u118 = arr5[118], u119 = arr5[119], u120 = arr5[120], u121 = arr5[121], u122 = arr5[122],
            u123 = arr5[123], u124 = arr5[124], u125 = arr5[125], u126 = arr5[126], u127 = arr5[127];
        int t0 = arr6[0], t1 = arr6[1], t2 = arr6[2], t3 = arr6[3], t4 = arr6[4], t5 = arr6[5], t6 = arr6[6],
            t7 = arr6[7], t8 = arr6[8], t9 = arr6[9], t10 = arr6[10], t11 = arr6[11], t12 = arr6[12],
            t13 = arr6[13], t14 = arr6[14], t15 = arr6[15], t16 = arr6[16], t17 = arr6[17], t18 = arr6[18],
            t19 = arr6[19], t20 = arr6[20], t21 = arr6[21], t22 = arr6[22], t23 = arr6[23], t24 = arr6[24],
            t25 = arr6[25], t26 = arr6[26], t27 = arr6[27], t28 = arr6[28], t29 = arr6[29], t30 = arr6[30],
            t31 = arr6[31], t32 = arr6[32], t33 = arr6[33], t34 = arr6[34], t35 = arr6[35], t36 = arr6[36],
            t37 = arr6[37], t38 = arr6[38], t39 = arr6[39], t40 = arr6[40], t41 = arr6[41], t42 = arr6[42],
            t43 = arr6[43], t44 = arr6[44], t45 = arr6[45], t46 = arr6[46], t47 = arr6[47], t48 = arr6[48],
            t49 = arr6[49], t50 = arr6[50], t51 = arr6[51], t52 = arr6[52], t53 = arr6[53], t54 = arr6[54],
            t55 = arr6[55], t56 = arr6[56], t57 = arr6[57], t58 = arr6[58], t59 = arr6[59], t60 = arr6[60],
            t61 = arr6[61], t62 = arr6[62], t63 = arr6[63], t64 = arr6[64], t65 = arr6[65], t66 = arr6[66],
            t67 = arr6[67], t68 = arr6[68], t69 = arr6[69], t70 = arr6[70], t71 = arr6[71], t72 = arr6[72],
            t73 = arr6[73], t74 = arr6[74], t75 = arr6[75], t76 = arr6[76], t77 = arr6[77], t78 = arr6[78],
            t79 = arr6[79], t80 = arr6[80], t81 = arr6[81], t82 = arr6[82], t83 = arr6[83], t84 = arr6[84],
            t85 = arr6[85], t86 = arr6[86], t87 = arr6[87], t88 = arr6[88], t89 = arr6[89], t90 = arr6[90],
            t91 = arr6[91], t92 = arr6[92], t93 = arr6[93], t94 = arr6[94], t95 = arr6[95], t96 = arr6[96],
            t97 = arr6[97], t98 = arr6[98], t99 = arr6[99], t100 = arr6[100], t101 = arr6[101], t102 = arr6[102],
            t103 = arr6[103], t104 = arr6[104], t105 = arr6[105], t106 = arr6[106], t107 = arr6[107],
            t108 = arr6[108], t109 = arr6[109], t110 = arr6[110], t111 = arr6[111], t112 = arr6[112],
            t113 = arr6[113], t114 = arr6[114], t115 = arr6[115], t116 = arr6[116], t117 = arr6[117],
            t118 = arr6[118], t119 = arr6[119], t120 = arr6[120], t121 = arr6[121], t122 = arr6[122],
            t123 = arr6[123], t124 = arr6[124], t125 = arr6[125], t126 = arr6[126], t127 = arr6[127];
        int s0 = arr7[0], s1 = arr7[1], s2 = arr7[2], s3 = arr7[3], s4 = arr7[4], s5 = arr7[5], s6 = arr7[6],
            s7 = arr7[7], s8 = arr7[8], s9 = arr7[9], s10 = arr7[10], s11 = arr7[11], s12 = arr7[12],
            s13 = arr7[13], s14 = arr7[14], s15 = arr7[15], s16 = arr7[16], s17 = arr7[17], s18 = arr7[18],
            s19 = arr7[19], s20 = arr7[20], s21 = arr7[21], s22 = arr7[22], s23 = arr7[23], s24 = arr7[24],
            s25 = arr7[25], s26 = arr7[26], s27 = arr7[27], s28 = arr7[28], s29 = arr7[29], s30 = arr7[30],
            s31 = arr7[31], s32 = arr7[32], s33 = arr7[33], s34 = arr7[34], s35 = arr7[35], s36 = arr7[36],
            s37 = arr7[37], s38 = arr7[38], s39 = arr7[39], s40 = arr7[40], s41 = arr7[41], s42 = arr7[42],
            s43 = arr7[43], s44 = arr7[44], s45 = arr7[45], s46 = arr7[46], s47 = arr7[47], s48 = arr7[48],
            s49 = arr7[49], s50 = arr7[50], s51 = arr7[51], s52 = arr7[52], s53 = arr7[53], s54 = arr7[54],
            s55 = arr7[55], s56 = arr7[56], s57 = arr7[57], s58 = arr7[58], s59 = arr7[59], s60 = arr7[60],
            s61 = arr7[61], s62 = arr7[62], s63 = arr7[63], s64 = arr7[64], s65 = arr7[65], s66 = arr7[66],
            s67 = arr7[67], s68 = arr7[68], s69 = arr7[69], s70 = arr7[70], s71 = arr7[71], s72 = arr7[72],
            s73 = arr7[73], s74 = arr7[74], s75 = arr7[75], s76 = arr7[76], s77 = arr7[77], s78 = arr7[78],
            s79 = arr7[79], s80 = arr7[80], s81 = arr7[81], s82 = arr7[82], s83 = arr7[83], s84 = arr7[84],
            s85 = arr7[85], s86 = arr7[86], s87 = arr7[87], s88 = arr7[88], s89 = arr7[89], s90 = arr7[90],
            s91 = arr7[91], s92 = arr7[92], s93 = arr7[93], s94 = arr7[94], s95 = arr7[95], s96 = arr7[96],
            s97 = arr7[97], s98 = arr7[98], s99 = arr7[99], s100 = arr7[100], s101 = arr7[101], s102 = arr7[102],
            s103 = arr7[103], s104 = arr7[104], s105 = arr7[105], s106 = arr7[106], s107 = arr7[107],
            s108 = arr7[108], s109 = arr7[109], s110 = arr7[110], s111 = arr7[111], s112 = arr7[112],
            s113 = arr7[113], s114 = arr7[114], s115 = arr7[115], s116 = arr7[116], s117 = arr7[117],
            s118 = arr7[118], s119 = arr7[119], s120 = arr7[120], s121 = arr7[121], s122 = arr7[122],
            s123 = arr7[123], s124 = arr7[124], s125 = arr7[125], s126 = arr7[126], s127 = arr7[127];
        int r0 = arr8[0], r1 = arr8[1], r2 = arr8[2], r3 = arr8[3], r4 = arr8[4], r5 = arr8[5], r6 = arr8[6],
            r7 = arr8[7], r8 = arr8[8], r9 = arr8[9], r10 = arr8[10], r11 = arr8[11], r12 = arr8[12],
            r13 = arr8[13], r14 = arr8[14], r15 = arr8[15], r16 = arr8[16], r17 = arr8[17], r18 = arr8[18],
            r19 = arr8[19], r20 = arr8[20], r21 = arr8[21], r22 = arr8[22], r23 = arr8[23], r24 = arr8[24],
            r25 = arr8[25], r26 = arr8[26], r27 = arr8[27], r28 = arr8[28], r29 = arr8[29], r30 = arr8[30],
            r31 = arr8[31], r32 = arr8[32], r33 = arr8[33], r34 = arr8[34], r35 = arr8[35], r36 = arr8[36],
            r37 = arr8[37], r38 = arr8[38], r39 = arr8[39], r40 = arr8[40], r41 = arr8[41], r42 = arr8[42],
            r43 = arr8[43], r44 = arr8[44], r45 = arr8[45], r46 = arr8[46], r47 = arr8[47], r48 = arr8[48],
            r49 = arr8[49], r50 = arr8[50], r51 = arr8[51], r52 = arr8[52], r53 = arr8[53], r54 = arr8[54],
            r55 = arr8[55], r56 = arr8[56], r57 = arr8[57], r58 = arr8[58], r59 = arr8[59], r60 = arr8[60],
            r61 = arr8[61], r62 = arr8[62], r63 = arr8[63], r64 = arr8[64], r65 = arr8[65], r66 = arr8[66],
            r67 = arr8[67], r68 = arr8[68], r69 = arr8[69], r70 = arr8[70], r71 = arr8[71], r72 = arr8[72],
            r73 = arr8[73], r74 = arr8[74], r75 = arr8[75], r76 = arr8[76], r77 = arr8[77], r78 = arr8[78],
            r79 = arr8[79], r80 = arr8[80], r81 = arr8[81], r82 = arr8[82], r83 = arr8[83], r84 = arr8[84],
            r85 = arr8[85], r86 = arr8[86], r87 = arr8[87], r88 = arr8[88], r89 = arr8[89], r90 = arr8[90],
            r91 = arr8[91], r92 = arr8[92], r93 = arr8[93], r94 = arr8[94], r95 = arr8[95], r96 = arr8[96],
            r97 = arr8[97], r98 = arr8[98], r99 = arr8[99], r100 = arr8[100], r101 = arr8[101], r102 = arr8[102],
            r103 = arr8[103], r104 = arr8[104], r105 = arr8[105], r106 = arr8[106], r107 = arr8[107],
            r108 = arr8[108], r109 = arr8[109], r110 = arr8[110], r111 = arr8[111], r112 = arr8[112],
            r113 = arr8[113], r114 = arr8[114], r115 = arr8[115], r116 = arr8[116], r117 = arr8[117],
            r118 = arr8[118], r119 = arr8[119], r120 = arr8[120], r121 = arr8[121], r122 = arr8[122],
            r123 = arr8[123], r124 = arr8[124], r125 = arr8[125], r126 = arr8[126], r127 = arr8[127];

        int k0   = x0   + y0   * z0   - w0   - v0   + u0   * t0   - s0 + r0,
            k1   = x1   + y1   * z1   + w1   - v1   + u1   * t1   - s1 + r1,
            k2   = x2   - y2   * z2   - w2   - v2   + u2   * t2   - s2 + r2,
            k3   = x3   - y3   * z3   + w3   - v3   + u3   * t3   - s3 + r3,
            k4   = x4   + y4   * z4   - w4   - v4   - u4   * t4   - s4 + r4,
            k5   = x5   + y5   * z5   + w5   - v5   - u5   * t5   - s5 + r5,
            k6   = x6   - y6   * z6   - w6   - v6   - u6   * t6   - s6 + r6,
            k7   = x7   - y7   * z7   + w7   - v7   - u7   * t7   - s7 + r7,
            k8   = x8   + y8   * z8   - w8   - v8   + u8   * t8   - s8 + r8,
            k9   = x9   + y9   * z9   + w9   - v9   + u9   * t9   - s9 + r9,
            k10  = x10  - y10  * z10  - w10  - v10  + u10  * t10  - s10 + r10,
            k11  = x11  - y11  * z11  + w11  - v11  + u11  * t11  - s11 + r11,
            k12  = x12  + y12  * z12  - w12  - v12  - u12  * t12  - s12 + r12,
            k13  = x13  + y13  * z13  + w13  - v13  - u13  * t13  - s13 + r13,
            k14  = x14  - y14  * z14  - w14  - v14  - u14  * t14  - s14 + r14,
            k15  = x15  - y15  * z15  + w15  - v15  - u15  * t15  - s15 + r15,
            k16  = x16  + y16  * z16  - w16  - v16  + u16  * t16  - s16 + r16,
            k17  = x17  + y17  * z17  + w17  - v17  + u17  * t17  - s17 + r17,
            k18  = x18  - y18  * z18  - w18  - v18  + u18  * t18  - s18 + r18,
            k19  = x19  - y19  * z19  + w19  - v19  + u19  * t19  - s19 + r19,
            k20  = x20  + y20  * z20  - w20  - v20  - u20  * t20  - s20 + r20,
            k21  = x21  + y21  * z21  + w21  - v21  - u21  * t21  - s21 + r21,
            k22  = x22  - y22  * z22  - w22  - v22  - u22  * t22  - s22 + r22,
            k23  = x23  - y23  * z23  + w23  - v23  - u23  * t23  - s23 + r23,
            k24  = x24  + y24  * z24  - w24  - v24  + u24  * t24  - s24 + r24,
            k25  = x25  + y25  * z25  + w25  - v25  + u25  * t25  - s25 + r25,
            k26  = x26  - y26  * z26  - w26  - v26  + u26  * t26  - s26 + r26,
            k27  = x27  - y27  * z27  + w27  - v27  + u27  * t27  - s27 + r27,
            k28  = x28  + y28  * z28  - w28  - v28  - u28  * t28  - s28 + r28,
            k29  = x29  + y29  * z29  + w29  - v29  - u29  * t29  - s29 + r29,
            k30  = x30  - y30  * z30  - w30  - v30  - u30  * t30  - s30 + r30,
            k31  = x31  - y31  * z31  + w31  - v31  - u31  * t31  - s31 + r31,
            k32  = x32  + y32  * z32  - w32  - v32  + u32  * t32  - s32 + r32,
            k33  = x33  + y33  * z34  + w33  - v33  + u33  * t33  - s33 + r33,
            k34  = x34  - y34  * z34  - w34  - v34  + u34  * t34  - s34 + r34,
            k35  = x35  - y35  * z35  + w35  - v35  + u35  * t35  - s35 + r35,
            k36  = x36  + y36  * z36  - w36  - v36  - u36  * t36  - s36 + r36,
            k37  = x37  + y37  * z37  + w37  - v37  - u37  * t37  - s37 + r37,
            k38  = x38  - y38  * z38  - w38  - v38  - u38  * t38  - s38 + r38,
            k39  = x39  - y39  * z39  + w39  - v39  - u39  * t39  - s39 + r39,
            k40  = x40  + y40  * z40  - w40  - v40  + u40  * t40  - s40 + r40,
            k41  = x41  + y41  * z41  + w41  - v41  + u41  * t41  - s41 + r41,
            k42  = x42  - y42  * z42  - w42  - v42  + u42  * t42  - s42 + r42,
            k43  = x43  - y43  * z43  + w43  - v43  + u43  * t43  - s43 + r43,
            k44  = x44  + y44  * z44  - w44  - v44  - u44  * t44  - s44 + r44,
            k45  = x45  + y45  * z45  + w45  - v45  - u45  * t45  - s45 + r45,
            k46  = x46  - y46  * z46  - w46  - v46  - u46  * t46  - s46 + r46,
            k47  = x47  - y47  * z47  + w47  - v47  - u47  * t47  - s47 + r47,
            k48  = x48  + y48  * z48  - w48  - v48  + u48  * t48  - s48 + r48,
            k49  = x49  + y49  * z49  + w49  - v49  + u49  * t49  - s49 + r49,
            k50  = x50  - y50  * z50  - w50  - v50  + u50  * t50  - s50 + r50,
            k51  = x51  - y51  * z51  + w51  - v51  + u51  * t51  - s51 + r51,
            k52  = x52  + y52  * z52  - w52  - v52  - u52  * t52  - s52 + r52,
            k53  = x53  + y53  * z53  + w53  - v53  - u53  * t53  - s53 + r53,
            k54  = x54  - y54  * z54  - w54  - v54  - u54  * t54  - s54 + r54,
            k55  = x55  - y55  * z55  + w55  - v55  - u55  * t55  - s55 + r55,
            k56  = x56  + y56  * z56  - w56  - v56  + u56  * t56  - s56 + r56,
            k57  = x57  + y57  * z57  + w57  - v57  + u57  * t57  - s57 + r57,
            k58  = x58  - y58  * z58  - w58  - v58  + u58  * t58  - s58 + r58,
            k59  = x59  - y59  * z59  + w59  - v59  + u59  * t59  - s59 + r59,
            k60  = x60  + y60  * z60  - w60  - v60  - u60  * t60  - s60 + r60,
            k61  = x61  + y61  * z61  + w61  - v61  - u61  * t61  - s61 + r61,
            k62  = x62  - y62  * z62  - w62  - v62  - u62  * t62  - s62 + r62,
            k63  = x63  - y63  * z63  + w63  - v63  - u63  * t63  - s63 + r63,
            k64  = x64  + y64  * z64  - w64  - v64  + u64  * t64  - s64 + r64,
            k65  = x65  + y65  * z65  + w65  - v65  + u65  * t65  - s65 + r65,
            k66  = x66  - y66  * z66  - w66  - v66  + u66  * t66  - s66 + r66,
            k67  = x67  - y67  * z67  + w67  - v67  + u67  * t67  - s67 + r67,
            k68  = x68  + y68  * z68  - w68  - v68  - u68  * t68  - s68 + r68,
            k69  = x69  + y69  * z69  + w69  - v69  - u69  * t69  - s69 + r69,
            k70  = x70  - y70  * z70  - w70  - v70  - u70  * t70  - s70 + r70,
            k71  = x71  - y71  * z71  + w71  - v71  - u71  * t71  - s71 + r71,
            k72  = x72  + y72  * z72  - w72  - v72  + u72  * t72  - s72 + r72,
            k73  = x73  + y73  * z73  + w73  - v73  + u73  * t73  - s73 + r73,
            k74  = x74  - y74  * z74  - w74  - v74  + u74  * t74  - s74 + r74,
            k75  = x75  - y75  * z75  + w75  - v75  + u75  * t75  - s75 + r75,
            k76  = x76  + y76  * z76  - w76  - v76  - u76  * t76  - s76 + r76,
            k77  = x77  + y77  * z77  + w77  - v77  - u77  * t77  - s77 + r77,
            k78  = x78  - y78  * z78  - w78  - v78  - u78  * t78  - s78 + r78,
            k79  = x79  - y79  * z79  + w79  - v79  - u79  * t79  - s79 + r79,
            k80  = x80  + y80  * z80  + w80  - v80  + u80  * t80  - s80 + r80,
            k81  = x81  - y81  * z81  - w81  - v81  + u81  * t81  - s81 + r81,
            k82  = x82  - y82  * z82  + w82  - v82  + u82  * t82  - s82 + r82,
            k83  = x83  + y83  * z83  - w83  - v83  - u83  * t83  - s83 + r83,
            k84  = x84  + y84  * z84  + w84  - v84  - u84  * t84  - s84 + r84,
            k85  = x85  - y85  * z85  - w85  - v85  - u85  * t85  - s85 + r85,
            k86  = x86  - y86  * z86  + w86  - v86  - u86  * t86  - s86 + r86,
            k87  = x87  + y87  * z87  - w87  - v87  + u87  * t87  - s87 + r87,
            k88  = x88  + y88  * z88  + w88  - v88  + u88  * t88  - s88 + r88,
            k89  = x89  - y89  * z89  - w89  - v89  + u89  * t89  - s89 + r89,
            k90  = x90  - y90  * z90  + w90  - v90  + u90  * t90  - s90 + r90,
            k91  = x91  + y91  * z91  - w91  - v91  - u91  * t91  - s91 + r91,
            k92  = x92  + y92  * z92  + w92  - v92  - u92  * t92  - s92 + r92,
            k93  = x93  - y93  * z93  - w93  - v93  - u93  * t93  - s93 + r93,
            k94  = x94  - y94  * z94  + w94  - v94  - u94  * t94  - s94 + r94,
            k95  = x95  + y95  * z95  - w95  - v95  + u95  * t95  - s95 + r95,
            k96  = x96  + y96  * z34  + w96  - v96  + u96  * t96  - s96 + r96,
            k97  = x97  - y97  * z97  - w97  - v97  + u97  * t97  - s97 + r97,
            k98  = x98  - y98  * z98  + w98  - v98  + u98  * t98  - s98 + r98,
            k99  = x99  + y99  * z99  - w99  - v99  - u99  * t99  - s99 + r99,
            k100 = x100 + y100 * z100 + w100 - v100 - u100 * t100 - s100 + r100,
            k101 = x101 - y101 * z101 - w101 - v101 - u101 * t101 - s101 + r101,
            k102 = x102 - y102 * z102 + w102 - v102 - u102 * t102 - s102 + r102,
            k103 = x103 + y103 * z103 - w103 - v103 + u103 * t103 - s103 + r103,
            k104 = x104 + y104 * z104 + w104 - v104 + u104 * t104 - s104 + r104,
            k105 = x105 - y105 * z105 - w105 - v105 + u105 * t105 - s105 + r105,
            k106 = x106 - y106 * z106 + w106 - v106 + u106 * t106 - s106 + r106,
            k107 = x107 + y107 * z107 - w107 - v107 - u107 * t107 - s107 + r107,
            k108 = x108 + y108 * z108 + w108 - v108 - u108 * t108 - s108 + r108,
            k109 = x109 - y109 * z109 - w109 - v109 - u109 * t109 - s109 + r109,
            k110 = x110 - y110 * z110 + w110 - v110 - u110 * t110 - s110 + r110,
            k111 = x111 + y111 * z111 - w111 - v111 + u111 * t111 - s111 + r111,
            k112 = x112 + y112 * z112 + w112 - v112 + u112 * t112 - s112 + r112,
            k113 = x113 - y113 * z113 - w113 - v113 + u113 * t113 - s113 + r113,
            k114 = x114 - y114 * z114 + w114 - v114 + u114 * t114 - s114 + r114,
            k115 = x115 + y115 * z115 - w115 - v115 - u115 * t115 - s115 + r115,
            k116 = x116 + y116 * z116 + w116 - v116 - u116 * t116 - s116 + r116,
            k117 = x117 - y117 * z117 - w117 - v117 - u117 * t117 - s117 + r117,
            k118 = x118 - y118 * z118 + w118 - v118 - u118 * t118 - s118 + r118,
            k119 = x119 + y119 * z119 - w119 - v119 + u119 * t119 - s119 + r119,
            k120 = x120 + y120 * z120 + w120 - v120 + u120 * t120 - s120 + r120,
            k121 = x121 - y121 * z121 - w121 - v121 + u121 * t121 - s121 + r121,
            k122 = x122 - y122 * z122 + w122 - v122 + u122 * t122 - s122 + r122,
            k123 = x123 + y123 * z123 - w123 - v123 - u123 * t123 - s123 + r123,
            k124 = x124 + y124 * z124 + w124 - v124 - u124 * t124 - s124 + r124,
            k125 = x125 - y125 * z125 - w125 - v125 - u125 * t125 - s125 + r125,
            k126 = x126 - y126 * z126 + w126 - v126 - u126 * t126 - s126 + r126,
            k127 = x127 - y127 * z127 + w127 - v127 - u127 * t127 - s127 - r127;

        return k0 + k1 + k2 + k3 + k4 + k5 + k6 + k7 + k8 + k9 + k10 + k11 + k12 + k13 + k14 + k15 +
               k16 + k17 + k18 + k19 + k20 + k21 + k22 + k23 + k24 + k25 + k26 + k27 + k28 + k29 + k30 + k31 +
               k32 + k33 + k34 + k35 + k36 + k37 + k38 + k39 + k40 + k41 + k42 + k43 + k44 + k45 + k46 + k47 +
               k48 + k49 + k50 + k51 + k52 + k53 + k54 + k55 + k56 + k57 + k58 + k59 + k60 + k61 + k62 + k63 +
               k64 + k65 + k66 + k67 + k68 + k69 + k70 + k71 + k72 + k73 + k74 + k75 + k76 + k77 + k78 + k79 +
               k80 + k81 + k82 + k83 + k84 + k85 + k86 + k87 + k88 + k89 + k90 + k91 + k92 + k93 + k94 + k95 +
               k96 + k97 + k98 + k99 + k100 + k101 + k102 + k103 + k104 + k105 + k106 + k107 + k108 + k109 + k110 +
               k111 + k112 + k113 + k114 + k115 + k116 + k117 + k118 + k119 + k120 + k121 + k122 + k123 + k124 + k125 +
               k126 + k127;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            testTinyFrame();
            testSmallFrame();
            testMediumFrame();
            testLargeFrame();
        }
        System.err.println("TEST PASSED");
    }
}
