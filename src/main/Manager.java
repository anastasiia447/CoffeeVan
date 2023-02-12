package main;

import main.helpers.FileManager;
import main.helpers.Searcher;
import main.helpers.Sorter;
import main.model.AggregateState;
import main.model.Coffee;
import main.model.CoffeeGoods;
import main.model.Van;

import java.util.ArrayList;
import java.util.UUID;

import static main.helpers.AppLogger.logger;

public class Manager {
    private ArrayList<Van> vans;
    private ArrayList<Coffee> coffees;
    private ArrayList<Coffee> foundCoffees;

    public Manager() {
        this.foundCoffees = new ArrayList<>();
        logger.info("Starts initialize Manager.");
        coffees = FileManager.readCoffee();
        vans = FileManager.readVans();
        ArrayList<CoffeeGoods> vansGoods = FileManager.readVanGoods();
        assignGoodsToTheirVan(vansGoods);
        logger.info("Initialize was finished successfully!");
    }

    public void saveData() {
        FileManager.saveData(coffees, vans);
    }

    public void addVan(Van van) {
        if (van.getCapacity() != 0) {
            vans.add(van);
            logger.info("Van {" + van.getId() + "} was added!");
        } else {
            logger.warning("method 'addVan' was skipped");
        }
    }

    public void changeVan(UUID id, Van van) {
        if (id != null) {
            for (int i = 0; i < vans.size(); i++) {
                var temp = vans.get(i).getId();
                if (temp.compareTo(id) == 0) {
                    vans.get(i).setData(van);
                    logger.info("Van {" + coffees.get(i).getId() + "} was changed!");
                    break;
                }
            }
        } else {
            logger.warning("method 'changeVan' was skipped");
        }
    }

    public void deleteVan(UUID id) {
        if (id != null) {
            // Search for van that must be deleted
            for (var van: vans) {
                if (van.getId().compareTo(id) == 0) {
                    vans.remove(van);
                    logger.info("Van was deleted!");
                    break;
                }
            }
        } else {
            logger.warning("method 'deleteVan' was skipped");
        }
    }

    public void printVans() {
        if (vans.size() != 0) {
            for (int i = 0; i < vans.size(); i++) {
                System.out.println(i + 1 + ". " + vans.get(i).toString());
            }
        } else {
            System.out.println("There are not any vans!");
        }
    }

    public void loadVan(UUID id) {
        if (id != null) {
            for (Van van : vans) {
                if (van.getId().compareTo(id) == 0) {
                    van.loadVan(coffees);
                    logger.info("Van {" + id + "} was uploaded!");
                }
            }
        } else {
            logger.warning("Method 'loadVan' was skipped!");
        }
    }

    /* Coffee methods*/

    public void addCoffee(Coffee coffee) {
        if (!coffee.isNull()) {
            coffees.add(coffee);
        } else {
            logger.warning("method 'addCoffee' was skipped!");
        }
    }

    public void changeCoffee(UUID id, Coffee coffee) {
        if (id != null) {
            for (Coffee value : coffees) {
                if (value.getId().compareTo(id) == 0) {
                    value.setData(coffee);
                    logger.info("Coffee {" + id + "} was changed!");
                    break;
                }
            }
        } else {
            logger.warning("method 'changeCoffee' was skipped!");
        }
    }

    public void deleteCoffee(UUID id) {
        if (id != null) {
            // Search for coffee that must be deleted
            for (int i = 0; i < coffees.size(); i++) {
                if (coffees.get(i).getId().compareTo(id) == 0) {
                    coffees.remove(i);
                    break;
                }
            }
            logger.info("Coffee was deleted!");
        } else {
            logger.warning("method 'deleteCoffee' was skipped!");
        }
    }

    public void printCoffees() {
        if (coffees.size() > 0) {
            for (int i = 0; i < coffees.size(); i++) {
                System.out.println(i + 1 + ". " + coffees.get(i).toString());
            }
        } else {
            System.out.println("There are not any vans!");
        }
    }

    public void sortCoffeeByQuality() {
        Sorter.sortByCoffeeQuality(coffees);
        logger.info("Coffee was sorted by quality!");
    }

    public void sortCoffeeByCorrelation() {
        Sorter.sortByCoffeeCorrelation(coffees);
        logger.info("Coffee was sorted by correlation!");
    }

    public void findCoffee(String name) {
        foundCoffees = Searcher.findCoffee(coffees, name);
        logger.info("Method 'findCoffee(String name)' finished his work!");
    }

    public void findCoffee(String name, AggregateState aggregateState) {
        foundCoffees = Searcher.findCoffee(coffees, name, aggregateState);
        logger.info("Method 'findCoffee(String name, AggregateState aggregateState)' finished his work!");
    }

    public void findCoffee(int quality) {
        foundCoffees = Searcher.findCoffee(coffees, quality);
        logger.info("Method 'findCoffee(int quality)' finished his work!");
    }

    public void findCoffee(int minQuality, int maxQuality) {
        foundCoffees = Searcher.findCoffee(coffees, minQuality, maxQuality);
        logger.info("Method 'findCoffee(int minQuality, int maxQuality)' finished his work!");
    }

    public void findCoffee(float minCorrelation, float maxCorrelation) {
        foundCoffees = Searcher.findCoffee(coffees, minCorrelation, maxCorrelation);
        logger.info("Method 'findCoffee(float minCorrelation, float maxCorrelation)' finished his work!");
    }

    public void getFoundCoffees() {
        if (coffees.size() != 0) {
            for (int i = 0; i < coffees.size(); i++) {
                System.out.println(i + 1 + ". " + foundCoffees.get(i).toString());
            }
        } else System.out.println("No match for this conditions!");
    }

    public void printVanGoods(UUID id) {
        for (var van: vans) {
            if (van.getId().compareTo(id) == 0) {
                System.out.println(van);
                for (var goods: van.getCoffeeGoods()) {
                    System.out.println(goods);
                }
            }
        }
    }

    /**
     * Using to find goods that assign to the van
     *
     * @param vansGoods
     */
    private void assignGoodsToTheirVan(ArrayList<CoffeeGoods> vansGoods) {
        // Foreach goods...
        for (CoffeeGoods vansGood : vansGoods) {
            ArrayList<CoffeeGoods> goods = new ArrayList<>();
            for (Van van : vans) {
                // Find van that we need
                if (vansGood.vanId().compareTo(van.getId()) == 0) {
                    // load van
                    van.loadVanByAdditionalGoods(goods);
                }
            }
        }
    }

    public ArrayList<Van> getVans() {
        return vans;
    }

    public ArrayList<Coffee> getCoffees() {
        return coffees;
    }

    public ArrayList<Coffee> getFoundCoffee() {
        return foundCoffees;
    }
}