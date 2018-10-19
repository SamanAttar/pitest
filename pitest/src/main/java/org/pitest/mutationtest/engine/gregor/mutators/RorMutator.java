package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;


//Saman - fancy way of saying - we are going to call a specific instance of this math mutator
//so basically, this is what is being called in the config
public enum RorMutator implements MethodMutatorFactory {

    ROR_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new RorMutatorVisitor(this, context, methodVisitor);
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        return name();
    }
}

class RorMutatorVisitor extends AbstractJumpMutator {

    RorMutatorVisitor(final MethodMutatorFactory factory,
                      final MutationContext context,
                      final MethodVisitor writer) {
        super(factory, context, writer);
    }

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();
    static final String message = "ROR Mutation";

    static {
        // MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB, "Replaced integer addition with subtraction"));

        /******  All the opcodes begin with == 0  ******/

        // Replace "if value == 0" with "if value != 0"
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFNE, message));

        // Replace "if value == 0" with "if value < 0"
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLT, message));

        // Replace "if value == 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGT, message));

        // Replace "if value == 0" with "if value >= 0"
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGE, message));

        // Replace "if value == 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLE, message));


        // Replace "if value != 0" with "if value == 0"
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFEQ, message));

        // Replace "if value <= 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, message));

        // Replace "if value <= 0" with "if value < 0"
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFLT, message));

        // Replace "if value >= 0" with "if value < 0"
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT, message));

        // Replace "if value >= 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGT, message));

        // Replace "if value > 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, message));

        // Replace "if value > 0" with "if value < 0"
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLT, message));

        // Replace "if value < 0" with "if value >= 0"
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE, message));

    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}
