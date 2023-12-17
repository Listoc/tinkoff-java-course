package edu.hw11.task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

@SuppressWarnings("MagicNumber")
public class Task3 {
    public static Class<?> getClassFib() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, Visibility.PUBLIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciAppender()))
            .make()
            .load(Object.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
    }

    private Task3() {}

    private static class FibonacciAppender implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            methodVisitor.visitCode();
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 4);

            Label label1 = new Label();
            Label label2 = new Label();

            methodVisitor.visitLabel(label2);
            methodVisitor.visitFrame(
                Opcodes.F_APPEND,
                3,
                new Object[]{Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER},
                0,
                null
            );

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);

            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label1);

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 4);
            methodVisitor.visitInsn(Opcodes.IADD);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 4);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 4);

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.IADD);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);

            methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);

            methodVisitor.visitLabel(label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 4);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitEnd();

            return new Size(2, 5);
        }
    }
}
