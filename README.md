# A tool for... Well, if you know, you know :)

## Version: Tested with 3.4.7. Should work with 3.2 too, but not guaranteed.

## REQUIREMENTS:
- Java 21+

### How to Use:

- Download the Repository to your PC.
- Open the Directory that contains all the Files (build.gradle, gradlew.bat, etc...)
- Put a copy of your original RewardData Config into this folder. You can get it from
  ``<game-server-directory>/data/txt/RewardData.txt``, and rename it to `RewardData.txt.original`
- Open ``src/main/java/me/lunatic/multiplier/Main.java``
    - Here, at the top you will find a List, called ``MULTIPLICATIONS``. Modify it to your needs. The existing example
      should give you a good understanding of the syntax. When you're done, save the file.
- Go back into the project directory, and run ``run-win.bat`` or ``run-linux.sh`` depending on your operating system.
- This will Load the existing RewardData, replace everything depending on your chosen multipliers, and spit out a
  modified ``RewardData.txt``, ready to use.
- Copy it to your server's txt directory, Start the Server, and enjoy.

## List of known issues:
- There are crashes that seem to be caused by too high values. I can confirm this when for when multiplying Primos (201) by 1.5, while 1.3 works just fine. Still trying to figure out what causes this...

## If you have issues with this that aren't you being dumb, open an issue in this Repo.
