package xyz.luan.games.hangman.spells;

/*
 * Amounts can be:
 = 0 : whole group
 > 0 : n elements of the group
 < 0 : (size - n) elements of the group
 */
/*
 * Groups are always strings in the following pattern
 1 : All
 + : Or (Union)
 - : Except (Diference)
 * : And (Intersection)
 With the sub-groups being chars and operations happening in the order they appear except if parenthesis are used
 Example:
 Sub-groups: A, B, C and D
 "A + B + C" : elements from A or B or C
 "(A + B) * (C - D)" : elements from (A or B) and from (C except D).
 "A" : elements from A
 "1 - A" : elements not from A

 etc.
 */
/*
 * The valid operations are:
   For effects:
     0 : set
     1 : add
     2 : multiply
     3 : greater (between current and operand)
     4 : lower
   For Requirements:
     1 : equal
     2 : greater than
     4 : lesser than
     3 : greater or equal to (1 and 2)
     5 : lesser or equal to (1 and 4)
     6 : different (2 or 4)
 */
public enum Action {
    // s : self, t : team, e : enemies
    TARGET_PLAYER("amount", "who"), CLEAR_PLAYER_TARGET(), MANA("operation", "value"), LIVES("operation", "value"),

    // where > w : word, p : targetted players' keyboards
    // how > u : unrevealed, r : revealed, b : blocked, o : owned by target
    // players, n : neutral, O : not owned by targe players
    TARGET_LETTER("amount", "where", "how"), CLEAR_LETTER_TARGET(), LETTER_STATUS("operator", "value"), LETTER_OWNERSHIP("operator",
            "value"),

    TARGET_TURN("start", "duration"), CLEAR_TURN_TARGET(), SKIP_TURN(), SKIP_PHASE("phase"),

    // how > u : unrevealed, r : revealed, b : blocked
    TARGET_SPELL("amount", "how"), CLEAR_SPELL_TARGET(), SPELL_STATUS("operator", "value"),

    SWAP,

    AND;

    private String[] validArguments;

    private Action(String... validArguments) {
        this.validArguments = validArguments;
    }
}