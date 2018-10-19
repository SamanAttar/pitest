package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

import java.util.HashMap;
import java.util.Map;

public enum RorMutator5 implements MethodMutatorFactory {

    ROR_MUTATOR5;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new RorMutatorVisitor5(this, context, methodVisitor);
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

class RorMutatorVisitor5 extends AbstractJumpMutator {

    RorMutatorVisitor5(final MethodMutatorFactory factory,
                       final MutationContext context,
                       final MethodVisitor writer) {
        super(factory, context, writer);
    }

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();
    static final String MESSAGE = "ROR Mutation 5.";

    static {
        // Replace "if value == 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLE, MESSAGE));


        // Replace "if value != 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLE, MESSAGE));

        // Replace "if value < 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLE, MESSAGE));

        // Replace "if value > 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, MESSAGE));

        // Replace "if value >= 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLE, MESSAGE));

        // Replace "if value <= 0" with "if value <= 0"
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGE, MESSAGE));

    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}
