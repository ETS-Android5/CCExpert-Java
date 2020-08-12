package fr.tordesillas.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class CrystalProcessor {
    private NumberFormat f;
    private static final int[] ATTACK_BY_LEVEL = {0, 8, 16, 24, 32, 41, 50, 59, 69, 79, 89, 99, 110, 121, 132, 144, 156, 168, 181, 194, 207, 221, 235, 250, 265, 280, 296, 312, 329, 346, 364, 382, 401, 420, 440, 460, 481, 502, 524, 547, 570, 594, 618, 643, 669, 695, 722, 750, 779, 809, 839, 870, 902, 935, 968, 1002, 1037, 1073, 1110, 1148, 1187, 1227, 1268, 1310, 1353, 1397, 1442, 1489, 1537, 1586, 1637, 1688, 1740, 1794, 1849, 1906, 1964, 2024, 2085, 2148, 2212, 2277, 2343, 2411, 2481, 2552, 2625, 2700, 2777, 2856, 2937, 3018, 3101, 3187, 3275, 3365, 3457, 3551, 3648, 3747, 3849};
    private static final int[] LIFE_BY_LEVEL = {0, 100, 206, 315, 427, 542, 661, 783, 909, 1039, 1173, 1310, 1451, 1596, 1746, 1900, 2059, 2222, 2390, 2563, 2741, 2923, 3110, 3303, 3501, 3705, 3915, 4131, 4353, 4582, 4817, 5057, 5303, 5556, 5817, 6085, 6361, 6645, 6937, 7237, 7546, 7860, 8182, 8513, 8854, 9204, 9564, 9934, 10314, 10705, 11107, 11515, 11934, 12364, 12806, 13260, 13727, 14207, 14700, 15207, 15727, 16254, 16795, 17351, 17922, 18508, 19110, 19728, 20363, 21015, 21685, 22360, 23052, 23763, 24492, 25241, 26010, 26799, 27609, 28440, 29293, 30148, 31025, 31924, 32847, 33793, 34764, 35760, 36781, 37828, 38902, 39980, 41086, 42219, 43381, 44572, 45793, 47044, 48327, 49642, 50990};
    private static final int[] CRYSTALS_BY_LEVEL = {0, 100, 110, 115, 121, 126, 134, 140, 147, 155, 163, 187, 196, 206, 217, 227, 240, 251, 263, 277, 290, 331, 346, 365, 382, 401, 421, 442, 463, 488, 510, 576, 602, 632, 665, 696, 731, 768, 805, 844, 886, 990, 1035, 1085, 1141, 1194, 1253, 1313, 1376, 1444, 1514, 1680, 1760, 1842, 1931, 2023, 2123, 2226, 2332, 2446, 2559, 2821, 2954, 3097, 3244, 3396, 3558, 3726, 3905, 4090, 4287, 4681, 4895, 5130, 5365, 5622, 5888, 6162, 6452, 6752, 7069, 100000, 120000, 144000, 172000, 205000, 244000, 290000, 343000, 405000, 477000, 560000, 656000, 766000, 892000, 1036000, 1200000, 1386000, 1597000, 1835000, 2103000};
    private static final int[] MANA_BY_LEVEL = {0, 10000, 12000, 14000, 16000, 18000, 20000, 22000, 24000, 26000, 28000, 32000, 36000, 40000, 44000, 48000, 52000, 56000, 60000, 64000, 68000, 74000, 80000, 86000, 92000, 98000, 104000, 110000, 116000, 122000, 128000, 136000, 144000, 152000, 160000, 168000, 176000, 184000, 192000, 200000, 208000, 218000, 228000, 238000, 248000, 258000, 268000, 278000, 288000, 298000, 308000, 320000, 332000, 344000, 356000, 368000, 380000, 392000, 404000, 416000, 428000, 442000, 456000, 470000, 484000, 498000, 512000, 526000, 540000, 554000, 568000, 584000, 600000, 616000, 632000, 648000, 664000, 680000, 696000, 712000, 728000, 746000, 764000, 782000, 800000, 818000, 836000, 854000, 872000, 890000, 908000, 928000, 948000, 968000, 988000, 1008000, 1028000, 1048000, 1068000, 1088000, 1108000};

    public CrystalProcessor() {
        f = NumberFormat.getNumberInstance(Locale.FRANCE);
    }

    public String computeAttack(int firstLevel, int secondLevel) {
        return f.format(ATTACK_BY_LEVEL[secondLevel] - ATTACK_BY_LEVEL[firstLevel]);
    }

    public String computeLife(int firstLevel, int secondLevel) {
        return f.format(LIFE_BY_LEVEL[secondLevel] - LIFE_BY_LEVEL[firstLevel]);
    }

    public String computeCrystal(int firstLevel, int secondLevel) {
        int crystals = 0;
        for (int i = firstLevel + 1; i <= secondLevel; i++) {
            crystals += CRYSTALS_BY_LEVEL[i];
        }
        return f.format(crystals);
    }

    public String computeMana(int firstLevel, int secondLevel) {
        int mana = 0;
        for (int i = firstLevel + 1; i <= secondLevel; i++) {
            mana += MANA_BY_LEVEL[i];
        }
        return f.format(mana);
    }
}
