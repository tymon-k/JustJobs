# Just Jobs!

## This plugin is about jobs in minecraft.
### In this plugin you have:
- Miner Job
- Fisherman Job
- Warrior Job
- Lumberjack Job
- Farmer Job
- Builder Job
- Trader Job
- Explorer Job

### Commands:
#### ```/jobs select``` - to select job
#### ```/jobs levelToMoney {Job}``` - to exchange level to money (Level resets to 0 and level 10 required)

### Admin Commands:
#### ```/jobs admin setXp {Player} {Job} {xp}``` - sets xp in the given job 
#### ```/jobs admin setLevel {Player} {Job} {level}``` - sets level in the given job 

### For each job you have some bonus effects:
- #### Miner:
  - ##### Adds haste {Job Level}
- #### Fisherman:
    - ##### Adds luck {Job Level}
- #### Warrior:
    - ##### Adds strength {{Job Level}/2}
- #### Lumberjack:
  - ##### {Job Level*10}% to place tree sapling
- #### Farmer:
  - ##### {Job Level*10}% to place seed
- #### Builder:
    - ##### Adds safe fall distance {Job Level*2} blocks
- #### Trader:
    - ##### Adds hero of the village {Job Level}
- #### Explorer:
  - ##### Adds speed {Job Level}
#### To get bonus effect from job level you have to select that job.
### Also it has some placeholders ([Placeholder API](https://github.com/PlaceholderAPI/PlaceholderAPI)):
- %JobsAndPoints_xp_{JobName}% to get xp level in the chosen job
- %JobsAndPoints_level_{JobName}% to get level in the chosen job
- %JobsAndPoints_fullXp_{JobName}% to get xp including other levels in the chosen job
- %JobsAndPoints_selectedJob% selected job by player
### Config (config.yaml):
- ```LevelUpXp``` how much xp someone must have to level up
- ```LevelToMoney``` how much player will get money from exchanging from level
- ```ExchangeSpeed``` how much from player will be taken xp when exchanging (per tick)
- ```StartExchangeLevel``` how much player must have level to start exchanging

###  Permissions:
  - jobs.levelToMoney (```/jobs levelToMoney```)
  - jobs.select (```/jobs select```)
  - jobs.admin.setLevel (```/jobs admin setLevel```)
  - jobs.admin.setXp (```/jobs admin setXp```)

| MC Version | Loader | Stable |
|:----------:|:------:|:------:|
|   26.1.2   | Paper  |   ✅    |

### dependencies:
- Vault
- Economy Manager (for example: EssentialsX Economy)


