/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package math.src.test.java.org.apache.mahout.math.jet.stat;

import org.apache.mahout.math.MahoutTestCase;
import org.junit.Test;

import java.util.Locale;

public final class ProbabilityTest extends MahoutTestCase {

  @Test
  public void testNormalCdf() {
    // computed by R
    // pnorm(seq(-5,5, length.out=100))
    double[] ref = {
            2.866516e-07, 4.816530e-07, 8.013697e-07, 1.320248e-06, 2.153811e-06,
            3.479323e-06, 5.565743e-06, 8.816559e-06, 1.383023e-05, 2.148428e-05,
            3.305072e-05, 5.035210e-05, 7.596947e-05, 1.135152e-04, 1.679855e-04,
            2.462079e-04, 3.574003e-04, 5.138562e-04, 7.317683e-04, 1.032198e-03,
            1.442193e-03, 1.996034e-03, 2.736602e-03, 3.716808e-03, 5.001037e-03,
            6.666521e-03, 8.804535e-03, 1.152131e-02, 1.493850e-02, 1.919309e-02,
            2.443656e-02, 3.083320e-02, 3.855748e-02, 4.779035e-02, 5.871452e-02,
            7.150870e-02, 8.634102e-02, 1.033618e-01, 1.226957e-01, 1.444345e-01,
            1.686293e-01, 1.952845e-01, 2.243525e-01, 2.557301e-01, 2.892574e-01,
            3.247181e-01, 3.618436e-01, 4.003175e-01, 4.397847e-01, 4.798600e-01,
            5.201400e-01, 5.602153e-01, 5.996825e-01, 6.381564e-01, 6.752819e-01,
            7.107426e-01, 7.442699e-01, 7.756475e-01, 8.047155e-01, 8.313707e-01,
            8.555655e-01, 8.773043e-01, 8.966382e-01, 9.136590e-01, 9.284913e-01,
            9.412855e-01, 9.522096e-01, 9.614425e-01, 9.691668e-01, 9.755634e-01,
            9.808069e-01, 9.850615e-01, 9.884787e-01, 9.911955e-01, 9.933335e-01,
            9.949990e-01, 9.962832e-01, 9.972634e-01, 9.980040e-01, 9.985578e-01,
            9.989678e-01, 9.992682e-01, 9.994861e-01, 9.996426e-01, 9.997538e-01,
            9.998320e-01, 9.998865e-01, 9.999240e-01, 9.999496e-01, 9.999669e-01,
            9.999785e-01, 9.999862e-01, 9.999912e-01, 9.999944e-01, 9.999965e-01,
            9.999978e-01, 9.999987e-01, 9.999992e-01, 9.999995e-01, 9.999997e-01
    };
    assertEquals(0.682689492137 / 2 + 0.5, Probability.normal(1), 1.0e-7);

    int i = 0;
    for (double x = -5; x <= 5.005; x += 10.0 / 99) {
      assertEquals("Test 1 cdf function at " + x, ref[i], Probability.normal(x), 1.0e-6);
      assertEquals("Test 2 cdf function at " + x, ref[i], Probability.normal(12, 1, x + 12), 1.0e-6);
      assertEquals("Test 3 cdf function at " + x, ref[i], Probability.normal(12, 0.25, x / 2.0 + 12), 1.0e-6);
      i++;
    }
  }

  @Test
  public void testBetaCdf() {
    // values computed using:
    //> pbeta(seq(0, 1, length.out=100), 1, 1)
    //> pbeta(seq(0, 1, length.out=100), 2, 1)
    //> pbeta(seq(0, 1, length.out=100), 2, 5)
    //> pbeta(seq(0, 1, length.out=100), 0.2, 5)
    //> pbeta(seq(0, 1, length.out=100), 0.2, 0.01)

    double[][] ref = new double[5][];
    
    ref[0] = new double[]{
            0.00000000, 0.01010101, 0.02020202, 0.03030303, 0.04040404, 0.05050505,
            0.06060606, 0.07070707, 0.08080808, 0.09090909, 0.10101010, 0.11111111,
            0.12121212, 0.13131313, 0.14141414, 0.15151515, 0.16161616, 0.17171717,
            0.18181818, 0.19191919, 0.20202020, 0.21212121, 0.22222222, 0.23232323,
            0.24242424, 0.25252525, 0.26262626, 0.27272727, 0.28282828, 0.29292929,
            0.30303030, 0.31313131, 0.32323232, 0.33333333, 0.34343434, 0.35353535,
            0.36363636, 0.37373737, 0.38383838, 0.39393939, 0.40404040, 0.41414141,
            0.42424242, 0.43434343, 0.44444444, 0.45454545, 0.46464646, 0.47474747,
            0.48484848, 0.49494949, 0.50505051, 0.51515152, 0.52525253, 0.53535354,
            0.54545455, 0.55555556, 0.56565657, 0.57575758, 0.58585859, 0.59595960,
            0.60606061, 0.61616162, 0.62626263, 0.63636364, 0.64646465, 0.65656566,
            0.66666667, 0.67676768, 0.68686869, 0.69696970, 0.70707071, 0.71717172,
            0.72727273, 0.73737374, 0.74747475, 0.75757576, 0.76767677, 0.77777778,
            0.78787879, 0.79797980, 0.80808081, 0.81818182, 0.82828283, 0.83838384,
            0.84848485, 0.85858586, 0.86868687, 0.87878788, 0.88888889, 0.89898990,
            0.90909091, 0.91919192, 0.92929293, 0.93939394, 0.94949495, 0.95959596,
            0.96969697, 0.97979798, 0.98989899, 1.00000000
    };
    ref[1] = new double[]{
            0.0000000000, 0.0001020304, 0.0004081216, 0.0009182736, 0.0016324865,
            0.0025507601, 0.0036730946, 0.0049994898, 0.0065299459, 0.0082644628,
            0.0102030405, 0.0123456790, 0.0146923783, 0.0172431385, 0.0199979594,
            0.0229568411, 0.0261197837, 0.0294867871, 0.0330578512, 0.0368329762,
            0.0408121620, 0.0449954086, 0.0493827160, 0.0539740843, 0.0587695133,
            0.0637690032, 0.0689725538, 0.0743801653, 0.0799918376, 0.0858075707,
            0.0918273646, 0.0980512193, 0.1044791348, 0.1111111111, 0.1179471483,
            0.1249872462, 0.1322314050, 0.1396796245, 0.1473319049, 0.1551882461,
            0.1632486481, 0.1715131109, 0.1799816345, 0.1886542190, 0.1975308642,
            0.2066115702, 0.2158963371, 0.2253851648, 0.2350780533, 0.2449750026,
            0.2550760127, 0.2653810836, 0.2758902153, 0.2866034078, 0.2975206612,
            0.3086419753, 0.3199673503, 0.3314967860, 0.3432302826, 0.3551678400,
            0.3673094582, 0.3796551372, 0.3922048771, 0.4049586777, 0.4179165391,
            0.4310784614, 0.4444444444, 0.4580144883, 0.4717885930, 0.4857667585,
            0.4999489848, 0.5143352719, 0.5289256198, 0.5437200286, 0.5587184981,
            0.5739210285, 0.5893276196, 0.6049382716, 0.6207529844, 0.6367717580,
            0.6529945924, 0.6694214876, 0.6860524436, 0.7028874605, 0.7199265381,
            0.7371696766, 0.7546168758, 0.7722681359, 0.7901234568, 0.8081828385,
            0.8264462810, 0.8449137843, 0.8635853484, 0.8824609734, 0.9015406591,
            0.9208244057, 0.9403122130, 0.9600040812, 0.9799000102, 1.0000000000
    };
    ref[2] = new double[]{
            0.000000000, 0.001489698, 0.005799444, 0.012698382, 0.021966298, 0.033393335,
            0.046779694, 0.061935356, 0.078679798, 0.096841712, 0.116258735, 0.136777178,
            0.158251755, 0.180545326, 0.203528637, 0.227080061, 0.251085352, 0.275437393,
            0.300035957, 0.324787463, 0.349604743, 0.374406809, 0.399118623, 0.423670875,
            0.447999763, 0.472046772, 0.495758466, 0.519086275, 0.541986291, 0.564419069,
            0.586349424, 0.607746242, 0.628582288, 0.648834019, 0.668481403, 0.687507740,
            0.705899486, 0.723646086, 0.740739801, 0.757175549, 0.772950746, 0.788065147,
            0.802520695, 0.816321377, 0.829473074, 0.841983426, 0.853861691, 0.865118615,
            0.875766302, 0.885818092, 0.895288433, 0.904192771, 0.912547431, 0.920369513,
            0.927676778, 0.934487554, 0.940820632, 0.946695177, 0.952130629, 0.957146627,
            0.961762916, 0.965999275, 0.969875437, 0.973411020, 0.976625460, 0.979537944,
            0.982167353, 0.984532203, 0.986650598, 0.988540173, 0.990218056, 0.991700827,
            0.993004475, 0.994144371, 0.995135237, 0.995991117, 0.996725360, 0.997350600,
            0.997878739, 0.998320942, 0.998687627, 0.998988463, 0.999232371, 0.999427531,
            0.999581387, 0.999700663, 0.999791377, 0.999858864, 0.999907798, 0.999942219,
            0.999965567, 0.999980718, 0.999990021, 0.999995342, 0.999998111, 0.999999376,
            0.999999851, 0.999999980, 0.999999999, 1.000000000
    };
    ref[3] = new double[]{
            0.0000000, 0.5858072, 0.6684658, 0.7201859, 0.7578936, 0.7873991, 0.8114552,
            0.8316029, 0.8487998, 0.8636849, 0.8767081, 0.8881993, 0.8984080, 0.9075280,
            0.9157131, 0.9230876, 0.9297536, 0.9357958, 0.9412856, 0.9462835, 0.9508414,
            0.9550044, 0.9588113, 0.9622963, 0.9654896, 0.9684178, 0.9711044, 0.9735707,
            0.9758356, 0.9779161, 0.9798276, 0.9815839, 0.9831977, 0.9846805, 0.9860426,
            0.9872936, 0.9884422, 0.9894965, 0.9904638, 0.9913509, 0.9921638, 0.9929085,
            0.9935900, 0.9942134, 0.9947832, 0.9953034, 0.9957779, 0.9962104, 0.9966041,
            0.9969621, 0.9972872, 0.9975821, 0.9978492, 0.9980907, 0.9983088, 0.9985055,
            0.9986824, 0.9988414, 0.9989839, 0.9991113, 0.9992251, 0.9993265, 0.9994165,
            0.9994963, 0.9995668, 0.9996288, 0.9996834, 0.9997311, 0.9997727, 0.9998089,
            0.9998401, 0.9998671, 0.9998901, 0.9999098, 0.9999265, 0.9999406, 0.9999524,
            0.9999622, 0.9999703, 0.9999769, 0.9999823, 0.9999866, 0.9999900, 0.9999927,
            0.9999947, 0.9999963, 0.9999975, 0.9999983, 0.9999989, 0.9999993, 0.9999996,
            0.9999998, 0.9999999, 0.9999999, 1.0000000, 1.0000000, 1.0000000, 1.0000000,
            1.0000000, 1.0000000
    };
    ref[4] = new double[]{
            0.00000000, 0.01908202, 0.02195656, 0.02385194, 0.02530810, 0.02650923,
            0.02754205, 0.02845484, 0.02927741, 0.03002959, 0.03072522, 0.03137444,
            0.03198487, 0.03256240, 0.03311171, 0.03363655, 0.03414001, 0.03462464,
            0.03509259, 0.03554568, 0.03598550, 0.03641339, 0.03683054, 0.03723799,
            0.03763667, 0.03802739, 0.03841091, 0.03878787, 0.03915890, 0.03952453,
            0.03988529, 0.04024162, 0.04059396, 0.04094272, 0.04128827, 0.04163096,
            0.04197113, 0.04230909, 0.04264515, 0.04297958, 0.04331268, 0.04364471,
            0.04397592, 0.04430658, 0.04463693, 0.04496722, 0.04529770, 0.04562860,
            0.04596017, 0.04629265, 0.04662629, 0.04696134, 0.04729804, 0.04763666,
            0.04797747, 0.04832073, 0.04866673, 0.04901578, 0.04936816, 0.04972422,
            0.05008428, 0.05044871, 0.05081789, 0.05119222, 0.05157213, 0.05195809,
            0.05235059, 0.05275018, 0.05315743, 0.05357298, 0.05399753, 0.05443184,
            0.05487673, 0.05533315, 0.05580212, 0.05628480, 0.05678247, 0.05729660,
            0.05782885, 0.05838111, 0.05895557, 0.05955475, 0.06018161, 0.06083965,
            0.06153300, 0.06226670, 0.06304685, 0.06388102, 0.06477877, 0.06575235,
            0.06681788, 0.06799717, 0.06932077, 0.07083331, 0.07260394, 0.07474824,
            0.07748243, 0.08129056, 0.08771055, 1.00000000
    };

    double[] alpha = {1.0, 2.0, 2.0, 0.2, 0.2};
    double[] beta = {1.0, 1.0, 5.0, 5.0, 0.01};
    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < 100; i++) {
        double x = i / 99.0;
        String p = String.format(Locale.ENGLISH,
                                 "pbeta(q=%6.4f, shape1=%5.3f shape2=%5.3f) = %.8f", 
                                 x, alpha[j], beta[j], ref[j][i]);
        assertEquals(p, ref[j][i], Probability.beta(alpha[j], beta[j], x), 1.0e-7);
      }
    }
  }

  @Test
  public void testLogGamma() {
    double[] xValues = {1.1, 2.1, 3.1, 4.1, 5.1, 20.1, 100.1, -0.9};
    double[] ref = {
            -0.04987244, 0.04543774, 0.78737508, 1.91877719, 3.32976417, 39.63719250, 359.59427179, 2.35807317
    };
    for (int i = 0; i < xValues.length; i++) {
      double x = xValues[i];
      assertEquals(ref[i], Gamma.logGamma(x), 1.0e-7);
    }
  }
}