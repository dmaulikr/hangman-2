package xyz.luan.games.hangman.game.forms;

import java.util.function.Supplier;

import xyz.luan.games.hangman.game.forms.fields.FormField;
import xyz.luan.games.hangman.game.forms.fields.TextFormField;

public class FormComponent {

    private String name;
    private Supplier<FormField> generator;

    public FormComponent(String name) {
        this(name, FormComponent::getTextField);
    }

    public FormComponent(String name, Supplier<FormField> generator) {
        this.name = name;
        this.generator = generator;
    }

    public String getName() {
        return name;
    }

    public FormField generate() {
        return this.generator.get();
    }

    public static FormField getTextField() {
        return new TextFormField();
    }
}
