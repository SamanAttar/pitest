package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;


//Saman - fancy way of saying - we are going to call a specific instance of this math mutator
//so basically, this is what is being called in the config
public enum RorMutator3 implements MethodMutatorFactory {

    ROR_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new RorMutatorVisitor3(this, context, methodVisitor);
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

class RorMutatorVisitor3 extends AbstractJumpMutator {

    RorMutatorVisitor3(final MethodMutatorFactory factory,
                       final MutationContext context,
                       final MethodVisitor writer) {
        super(factory, context, writer);
    }

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {

        String MESSAGE = "ROR Mutation 3, Replace \"if value == 0\" with \"if value > 0\"";
        // Replace "if value == 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGT, MESSAGE));

        MESSAGE = "ROR Mutation 3, Replace \"if value != 0\" with \"if value > 0\"";
        // Replace "if value != 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGT, MESSAGE));

        MESSAGE = "ROR Mutation 3, Replace \"if value < 0\" with \"if value > 0\"";
        // Replace "if value < 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGT, MESSAGE));

        MESSAGE = "ROR Mutation 3, Replace \"if value > 0\" with \"if value < 0\"";
        // Replace "if value > 0" with "if value < 0"
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFNE, MESSAGE));

        MESSAGE = "ROR Mutation 3, Replace \"if value >= 0\" with \"if value > 0\"";
        // Replace "if value >= 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGT, MESSAGE));

        MESSAGE = "ROR Mutation 3, Replace \"if value <= 0\" with \"if value > 0\"";
        // Replace "if value <= 0" with "if value > 0"
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, MESSAGE));
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}
