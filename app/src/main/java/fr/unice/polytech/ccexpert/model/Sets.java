package fr.unice.polytech.ccexpert.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Sets {
    private static Sets mInstance = null;

    private Map<Integer, Hero> heroesIds;
    private Map<String, Hero> heroesNames;
    private List<Dungeon> dungeonSet;
    private Map<String, Talent> talents;
    private Map<String, Pet> pets;
    private List<Archdemon> archdemons;

    private Sets() {
        heroesIds = new HashMap<>();
        heroesNames = new HashMap<>();
        dungeonSet = new ArrayList<>();
        talents = new HashMap<>();
        pets = new HashMap<>();
        archdemons = new ArrayList<>();
    }

    public static Sets getInstance() {
        if (mInstance == null) {
            mInstance = new Sets();
        }
        return mInstance;
    }

    void addHero(Hero hero, int id) {
        heroesIds.put(id, hero);
        heroesNames.put(hero.getFrenchName(), hero);
    }

    void addDungeon(Dungeon dungeon) {
        dungeonSet.add(dungeon);
    }

    void addTalent(Talent talent) {
        talents.put(talent.getFrenchName(), talent);
    }

    void addPet(Pet pet) {
        pets.put(pet.getFrenchName(), pet);
    }

    void addArchdemon(Archdemon archdemon) {
        archdemons.add(archdemon);
    }

    public Hero getHero(String name) {
        return heroesNames.get(name);
    }

    public Talent getTalent(String name) {
        return talents.get(name);
    }

    public Pet getPet(String name) {
        return pets.get(name);
    }

    public Collection<Dungeon> getDungeonSet(int door, int base) {
        Collection<Dungeon> matchingDungeons = new HashSet<>();
        for (Dungeon dungeon : dungeonSet) {
            if (dungeon.getBase() == base && dungeon.getDoor() == door) {
                matchingDungeons.add(dungeon);
            }
        }
        return matchingDungeons;
    }

    public List<Hero> getHeroSorted(boolean byName) {
        List<Hero> heroes = new ArrayList<>();
        if (byName) {
            heroes = getHeroesListSorted();
        } else {
            List<Integer> sortedKeys = new ArrayList<>(heroesIds.keySet());
            Collections.sort(sortedKeys);
            Collections.reverse(sortedKeys);
            for (int id : sortedKeys) {
                heroes.add(heroesIds.get(id));
            }
        }
        return heroes;
    }

    private List<Hero> getHeroesListSorted() {
        List<String> nameSorted = new ArrayList<>();
        List<Hero> heroesSorted = new ArrayList<>();

        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                nameSorted = new ArrayList<>(heroesNames.keySet());
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    heroesSorted.add(heroesNames.get(name));
                }
                break;
            case "german":
            case "deutsch":
                Map<String, Hero> geHeroes = new HashMap<>();
                for (Hero hero : heroesNames.values()) {
                    nameSorted.add(hero.getGermanName());
                    geHeroes.put(hero.getGermanName(), hero);
                }
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    heroesSorted.add(geHeroes.get(name));
                }
                break;
            default:
                Map<String, Hero> enHeroes = new HashMap<>();
                for (Hero hero : heroesNames.values()) {
                    nameSorted.add(hero.getEnglishName());
                    enHeroes.put(hero.getEnglishName(), hero);
                }
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    heroesSorted.add(enHeroes.get(name));
                }
        }

        return heroesSorted;
    }

    public Archdemon getArchdemon(int position) {
        return archdemons.get(position);
    }

    public int getArchdemonsSize() {
        return archdemons.size();
    }

    public List<Talent> getTalents() {
        List<Talent> talentList = new ArrayList<>();
        for (Talent t : talents.values()) {
            if (!t.isEnchantment()) {
                talentList.add(t);
            }
        }
        return talentList;
    }

    public List<Talent> getEnchantments() {
        List<Talent> enchantmentList = new ArrayList<>();
        for (Talent t : talents.values()) {
            if (t.isEnchantment()) {
                enchantmentList.add(t);
            }
        }
        return enchantmentList;
    }
}
