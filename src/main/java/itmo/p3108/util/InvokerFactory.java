package itmo.p3108.util;

import itmo.p3108.command.no_argument_command.*;
import itmo.p3108.command.one_argument_command.*;

/**
 * Factory for invoker,add commands
 */
public class InvokerFactory {
    private InvokerFactory() {
    }

    public static Invoker createInvoker() {
        Invoker invoker = Invoker.getInstance();
        invoker.add(
                PrintDescending.getInstance(),
                Reorder.getInstance(),
                Help.getInstance(),
                Clear.getInstance(),
                Info.getInstance(),
                Show.getInstance(),
                Add.getInstance(),
                Update.getInstance(),
                RemoveById.getInstance(),
                Exit.getInstance(),
                CountByHeight.getInstance(),
                FilterStartsWithName.getInstance(),
                AddIfMax.getInstance(),
                RemoveGreater.getInstance(),
                Save.getInstance(),
                ExecuteScript.getInstance(),
                CheckFail.SetPath.getInstance());
        return invoker;

    }
}
