package com.invadermonky.spartanweaponryarcanaex.util.libs;

public class LibAttributes {
    public static class Ranged {
        public static int[] soulBracket = new int[]{16, 60, 200, 400, 1000, 2000, 4000};
        public static float[] velocityAdded = new float[]{0.25F, 0.5F, 0.75F, 1.0F, 1.25F, 1.5F, 1.75F};
        public static double[] destructiveChargeTicksModifier = new double[] {0.10, 0.2, 0.30, 0.40, 0.6, 0.8, 1.0};
        public static double[] vengefulChargeTicksModifier = new double[] {-0.15, -0.20, -0.25, -0.3, -0.4, -0.4, -0.5};

        //Effect Values
        public static float[] destructiveExplosionRadius = new float[] {0.5F, 1.0F, 1.5F, 2.0F, 2.5F, 3.0F, 3.5F};
        public static int[] poisonDuration = new int[] {50, 100, 150, 80, 120, 160, 200};
        public static int[] poisonLevel = new int[] {0, 0, 0, 1, 1, 1, 1};
        public static int[] levitationDuration = new int[] {20, 40, 60, 80, 100, 120, 160};
        public static int[] levitationLevel = new int[] {0, 0, 0, 1, 1, 1, 2};
        public static int[] slownessDuration = new int[] {40, 60, 100, 150, 200, 250, 300};
        public static int[] slownessLevel = new int[] {0, 0, 0, 1, 1, 1, 2};
    }

    public static class Shields {
        public static int[] soulBracket = new int[]{16, 60, 200, 400, 1000, 2000, 4000};
        public static double[] defaultArmorAdded = new double[] {1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 6.0};
        public static double[] destructiveDamageAdded = new double[]{-0.5, 0, 0.5, 1.0, 1.5, 2.0, 2.0};
        public static double[] steadfastToughnessAdded = new double[] {0.0, 0.5, 1.0, 1.0, 1.5, 2.0, 2.5};
        public static double[] vengefulSpeedAdded = new double[] {0.05, 0.75, 0.10, 0.125, 0.15, 0.175, 0.20};
    }

    public static class Swords {
        public static int[] soulBracket = new int[]{16, 60, 200, 400, 1000, 2000, 4000};
        public static double[] defaultDamageAdded = new double[]{1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
        public static double[] destructiveDamageAdded = new double[]{1.5, 2.25, 3.0, 3.75, 4.5, 5.25, 6.0};
        public static double[] vengefulDamageAdded = new double[]{0.0, 0.5, 1.0, 1.5, 2.0, 2.25, 2.5};
        public static double[] steadfastDamageAdded = new double[]{0.0, 0.5, 1.0, 1.5, 2.0, 2.25, 2.5};
        public static double[] soulDrainPerSwing = new double[]{0.05, 0.1, 0.2, 0.4, 0.75, 1.0, 1.25};
        public static double[] soulDrop = new double[]{2.0, 4.0, 7.0, 10.0, 13.0, 15.0, 18.0};
        public static double[] staticDrop = new double[]{1.0, 1.0, 2.0, 3.0, 3.0, 4.0, 4.0};
        public static double[] healthBonus = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        /* Sentient Sword Values:
            Base Attack Speed: -2.4
            public static double[] destructiveAttackSpeed = new double[]{-2.6, -2.7, -2.8, -2.9, -3.0, -3.0, -3.0};
            public static double[] vengefulAttackSpeed = new double[]{-2.1, -2.0, -1.8, -1.7, -1.6, -1.6, -1.5};
        */
        public static double[] destructiveAttackSpeedModifier = new double[]{-0.2, -0.3, -0.4, -0.5, -0.6, -0.6, -0.6};
        public static double[] vengefulAttackSpeedModifier = new double[]{0.3, 0.4, 0.6, 0.7, 0.8, 0.8, 0.9};
        public static double[] defaultDigSpeedAdded = new double[]{1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 6.0};
        public static int[] absorptionTime = new int[]{200, 300, 400, 500, 600, 700, 800};
        public static double maxAbsorptionHearts = 10.0;
        public static int[] poisonTime = new int[]{25, 50, 60, 80, 100, 120, 150};
        public static int[] poisonLevel = new int[]{0, 0, 0, 1, 1, 1, 1};
        public static double[] movementSpeed = new double[]{0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.4};
    }
}
