package advent.of.code.year2022.day19

data class MaterialStorage(val ores: Int, val clays: Int, val obsidian: Int, val geodes: Int) {
    fun addMaterials(ores: Int, clays: Int, obsidian: Int, geodes: Int): MaterialStorage {
        return MaterialStorage(this.ores + ores, this.clays + clays, this.obsidian + obsidian, this.geodes + geodes)
    }

    fun spendMaterial(ores: Int, clays: Int, obsidian: Int): MaterialStorage {
        return addMaterials(-ores, -clays, -obsidian, 0);
    }
}

data class MachineStorage(val oreRobots: Int, val clayRobots: Int, val obsidianRobots: Int, val geodeRobots: Int) {
    fun addMachines(oreRobots: Int, clayRobots: Int, obsidianRobots: Int, geodeRobots: Int): MachineStorage {
        return MachineStorage(
            this.oreRobots + oreRobots,
            this.clayRobots + clayRobots,
            this.obsidianRobots + obsidianRobots,
            this.geodeRobots + geodeRobots
        )
    }
}

data class Storage(
     val blueprint: Blueprint,
     val materialStorage: MaterialStorage,
     val machineStorage: MachineStorage
) {

    fun collect(): Storage {
        return this.copy(
            materialStorage = materialStorage.addMaterials(
                machineStorage.oreRobots,
                machineStorage.clayRobots,
                machineStorage.obsidianRobots,
                machineStorage.geodeRobots
            )
        )
    }

    fun canCraftOreRobot(): Boolean {
        // LETS ASSUME ITS NOT WORT TO CRAFT IF YOU HAVE ENOUGH
        return this.materialStorage.ores >= blueprint.oreRobotPrice.orePrice && this.machineStorage.oreRobots < blueprint.maxOresNeeded
    }

    fun canCraftClayRobot(): Boolean {
        return this.materialStorage.ores >= blueprint.clayRobotPrice.orePrice && this.machineStorage.clayRobots < blueprint.maxClaysNeeded
    }

    fun canCraftObsidianRobot(): Boolean {
        return this.materialStorage.ores >= blueprint.obsidianRobotPrice.orePrice &&
                this.materialStorage.clays >= blueprint.obsidianRobotPrice.clayPrice &&
                this.machineStorage.obsidianRobots < blueprint.maxObsidianNeeded
    }

    fun canCraftGeodes(): Boolean {
        return this.materialStorage.ores >= blueprint.geodeRobotPrice.orePrice &&
                this.materialStorage.clays >= blueprint.geodeRobotPrice.clayPrice &&
                this.materialStorage.obsidian >= blueprint.geodeRobotPrice.obsidianPrice
    }

    fun craftOreMachine(): Storage {
        return if (!canCraftOreRobot()) {
            this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.copy(),
                machineStorage = machineStorage.copy()
            )
        } else {
            return this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.spendMaterial(blueprint.oreRobotPrice.orePrice, 0, 0),
                machineStorage = machineStorage.addMachines(1, 0, 0, 0)
            )
        }
    }

    fun craftClayMachine(): Storage {
        return if (!canCraftClayRobot()) {
            this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.copy(),
                machineStorage = machineStorage.copy()
            )
        } else {
            return this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.spendMaterial(blueprint.clayRobotPrice.orePrice, 0, 0),
                machineStorage = machineStorage.addMachines(0, 1, 0, 0)
            )
        }
    }


    fun craftObsidianMachine(): Storage {
        return if (!canCraftObsidianRobot()) {
            this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.copy(),
                machineStorage = machineStorage.copy()
            )
        } else {
            return this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.spendMaterial(
                    blueprint.obsidianRobotPrice.orePrice,
                    blueprint.obsidianRobotPrice.clayPrice,
                    0
                ),
                machineStorage = machineStorage.addMachines(0, 0, 1, 0)
            )
        }
    }

    fun craftGeodeMachine(day:Int): Storage {
        println("Purchasing geon on $day : AND $this")
        return if (!canCraftGeodes()) {
            this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.copy(),
                machineStorage = machineStorage.copy()
            )
        } else {
            return this.copy(
                blueprint = blueprint,
                materialStorage = materialStorage.spendMaterial(
                    blueprint.geodeRobotPrice.orePrice,
                    blueprint.geodeRobotPrice.clayPrice,
                    blueprint.geodeRobotPrice.obsidianPrice
                ),
                machineStorage = machineStorage.addMachines(0, 0, 0, 1)
            )
        }
    }
}


