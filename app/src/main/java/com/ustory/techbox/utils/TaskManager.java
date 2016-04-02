
package com.ustory.techbox.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;

public class TaskManager
{
    /**
     * Execute task message.
     */
    private static final int MESSAGE_POST_EXECUTE  = 0x01;

    /**
     * Update progress message.
     */
    private static final int MESSAGE_POST_PROGRESS = 0x02;

    /**
     * The state change listener.
     */
    public interface IStateChangeListener
    {
        /**
         * Called when the task manager's state is changed. This method will be called in
         * UI thread.
         *
         * @param taskManager Which task manager's state changed.
         * @param oldState The old state.
         * @param newState The new state.
         */
        public void onStateChanged(TaskManager taskManager, State oldState, State newState);
    }

    /**
     * A representation of a task manager's state. A given thread may only be in one
     * state at a time.
     */
    public enum State
    {
        /**
         * The task manager has been created, but has never been started.
         */
        NEW,

        /**
         * Indicate the task manager is running one task.
         */
        RUNNING,

        /**
         * Indicate the task manager is paused, typically call {@link #pause()} method.
         */
        PAUSED,

        /**
         * All tasks are finished.
         */
        FINISHED,
    }

    /**
     * The status of the {@link TaskManager} class.
     */
    public enum TaskManagerState
    {
        /**
         * Continue the task manager to run next task.
         */
        CONTINUE,

        /**
         * Indicate the task manager pause to run next task.
         */
        PAUSE,
    }

    /**
     * The running task manager collection.
     */
    private static HashMap<String, TaskManager> s_taskManagers = new HashMap<String, TaskManager>();

    /**
     * The task list.
     */
    private LinkedList<Task> mTaskList = new LinkedList<Task>();

    /**
     * The task operation, it will pass from first task to the last task.
     */
    private TaskOperation mTaskOperation  = new TaskOperation();

    private ThreadWorker mThreadWorker   = null;

    /**
     * The current perform task, may be null.
     */
    private Task mCurTask        = null;

    /**
     * The state of the task manager.
     */
    private State               mState          = State.NEW;

    /**
     * The name of the task manager.
     */
    private String              mName           = null;

    /**
     * The listener.
     */
    private IStateChangeListener mListener      = null;

    /**
     * The background thread handler, which is associated to a background thread looper.
     */
    private Handler             mThreadHandler  = null;

    /**
     * The UI thread handler.
     */
    private Handler             mUIHandler      = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MESSAGE_POST_EXECUTE:
                    Task task = (Task)msg.obj;
                    executeTask(task);
                    // Try to run next task if possible.
                    runNextTask();
                    break;

                case MESSAGE_POST_PROGRESS:
                    postProgress(msg.obj);
                    break;
            }
        }
    };

    /**
     * The constructor method.
     */
    public TaskManager()
    {
    }

    /**
     * The constructor method.
     *
     * @param name The name of the task manager.
     */
    public TaskManager(String name)
    {
        mName = name;
    }

    /**
     * Add the task to {@link TaskManager} class.
     *
     * @param task The task.
     *
     * @return the {@link TaskManager} object.
     */
    public TaskManager next(Task task)
    {
        if (null != task)
        {
            synchronized (mTaskList)
            {
                int id = mTaskList.size() + 1;
                task.setTaskId(id);
                mTaskList.add(task);
            }
        }
        else
        {
            throw new NullPointerException("task is null");
        }

        return this;
    }

    /**
     * Start to execute the tasks in the task manager.
     */
    public void execute()
    {
        if (mTaskList.size() > 0)
        {
            startThread();

            // Set the task to RUNNING.
            setState(State.RUNNING);

            // Perform the runnable in the handler which is associated to the background thread. 
            mThreadHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    doInBackground();
                }
            });
        }
        else
        {
            quitLooper();
        }
    }

    /**
     * Start to execute the tasks in the task manager with the specified parameter.
     *
     * @param operation The task operation contains the task parameter.
     */
    public void execute(TaskOperation operation)
    {
        if (null != operation)
        {
            mTaskOperation = operation;
        }

        execute();
    }

    /**
     * Post execute a task which will be running in UI thread.
     *
     * @param task the task to be running.
     */
    public void postExecute(Task task)
    {
        if (null == task)
        {
            throw new NullPointerException("Task can NOT be null.");
        }

        final Task runTask = task;

        // If the task running status is UI_THREAD.
        if (Task.RunningStatus.UI_THREAD == runTask.getRunningStatus())
        {
            // The task is running in UI thread.
            mUIHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    executeTask(runTask);
                }
            });
        }
    }

    /**
     * Publish the task progress, if you call this method, the {@link Task#onProgressUpdate(Object)}
     * method will be called, which is running in the UI thread.
     *
     * @param progresses The progress.
     */
    public void publishProgress(Object progresses)
    {
        mUIHandler.obtainMessage(MESSAGE_POST_PROGRESS, progresses).sendToTarget();
    }

    /**
     * Cancel the current running task.
     */
    public void cancelCurrentTask()
    {
        if (null != mCurTask)
        {
            mCurTask.cancel();
        }
    }

    /**
     * Remove the tasks in the list.
     */
    public void removeTasks()
    {
        synchronized (mTaskList)
        {
            if (mTaskList.size() > 0)
            {
                mTaskList.clear();
                quitLooper();
            }
        }
    }

    /**
     * Remove the specified task.
     *
     * @param task The task to be removed.
     */
    public void removeTask(Task task)
    {
        synchronized (mTaskList)
        {
            mTaskList.remove(task);

            if (mTaskList.isEmpty())
            {
                quitLooper();
            }
        }
    }

    /**
     * Set the state change listener.
     *
     * @param listener
     */
    public void setStateChangeListener(IStateChangeListener listener)
    {
        mListener = listener;
    }

    /**
     * Get the task operation.
     *
     * @return
     */
    public TaskOperation getTaskOperation()
    {
        return mTaskOperation;
    }

    /**
     * @return the mName
     */
    public String getName()
    {
        return mName;
    }

    /**
     * Pause the worker thread.
     */
    public void pause()
    {
        if (null != mThreadWorker)
        {
            setState(State.PAUSED);

           // mThreadWorker.pause();
        }
    }

    /**
     * Resume the worker thread from the waiting status.
     */
    public void resume()
    {
        if (null != mThreadWorker)
        {
            setState(State.RUNNING);

            //mThreadWorker.restart();
        }
    }

    /**
     * Quit the looper so that the thread can finish correctly.
     */
    public void quitLooper()
    {
        if (null != mThreadWorker)
        {
            mThreadWorker.quit();
            mThreadWorker = null;
        }

        mThreadHandler = null;

        // Set the task to FINISHED.
        setState(State.FINISHED);
    }

    /**
     * Blocks the current thread ({@link Thread#currentThread()}) until the receiver finishes its execution and dies.
     */
    public final void join()
    {
        if (null != mThreadWorker)
        {
            //mThreadWorker.join();
        }
    }

    /**
     * Get the task manager state.
     *
     * @return
     */
    public State getState()
    {
        return mState;
    }

    /**
     * Get the running task manager.
     *
     * @return HashMap<String, TaskManager>, the task manager's name is the key, and the
     * task manager object is the value.
     */
    public static HashMap<String, TaskManager> getTaskManagers()
    {
        return s_taskManagers;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Name = ").append(mName).append("  ");
        sb.append("State = ").append(mState).append("  ");
        sb.append(super.toString());

        return sb.toString();
    }

    /**
     * print task execute status
     *
     * @param task
     */
    protected void printExecuteTaskState(Task task)
    {
        Log.d("TaskManager", "    Executer the task: " + task.toString());
    }

    /**
     * Set the state.
     *
     * @param state
     */
    private void setState(State state)
    {
        final State oldState = mState;
        final State newState = state;
        mState = state;

        if (mState == State.FINISHED)
        {
            popTaskManager(this);
        }
        else
        {
            pushTaskManager(this);
        }

        if (oldState != newState)
        {
            printTaskManagerState(oldState, newState);
            performStateChange(oldState, newState);
        }
    }

    /**
     * Call this method to start the work thread if can.
     */
    private void startThread()
    {
        if (null == mThreadWorker)
        {
            String name = TextUtils.isEmpty(mName) ? this.toString() : mName;
            String threadName = "TaskManager_Thread_" + name;
            mThreadWorker  = new ThreadWorker(threadName);
            mThreadHandler = new Handler(mThreadWorker.getLooper());
        }
    }

    /**
     * This method is running in the background thread.
     */
    private void doInBackground()
    {
        mCurTask = null;

        if (mTaskList.isEmpty())
        {
            return;
        }

        Task task = mTaskList.get(0);
        mCurTask = task;

        // Remove the first item in the list.
        synchronized (mTaskList)
        {
            mTaskList.remove(0);
        }

        // If the task is allowed to be running in background thread, we execute the task
        // now, the doInBackground() method is running in the background thread.
        switch (task.getRunningStatus())
        {
            case WORK_THREAD:
                executeTask(task);
                // Try to run next task if possible.
                runNextTask();
                break;

            case UI_THREAD:
                // Send a message to the UI handler to executer the task.
                mUIHandler.obtainMessage(MESSAGE_POST_EXECUTE, task).sendToTarget();
                break;
        }
    }

    /**
     * Run the next task.
     */
    private void runNextTask()
    {
        // If run next, call the execute() method again.
        if (isRunNext())
        {
            execute();
        }
    }

    /**
     * Check whether run the next task if has one.
     *
     * @return true if run next task, otherwise false.
     */
    private boolean isRunNext()
    {
        boolean isRunNext = true;
        boolean hasNext   = false;

        if (null != mTaskOperation)
        {
            isRunNext = (mTaskOperation.getTaskManagerStatus() == TaskManagerState.CONTINUE);
        }

        if (null != mTaskList)
        {
            hasNext = mTaskList.size() > 0;
        }

        // No next task, quit the thread.
        if (!hasNext)
        {
            quitLooper();
        }

        return (isRunNext && hasNext);
    }

    /**
     * Execute the task, if will call {@link Task#onExecute(TaskOperation)} method.
     *
     * @param task The task object.
     */
    private void executeTask(Task task)
    {
        if (null != task)
        {


            // Set the status of the task.
            task.setStatus(Task.Status.RUNNING);

            // Print the task state.
            this.printExecuteTaskState(task);

            try
            {
                // Avoid the exception from task interrupting the task manager works.
                mTaskOperation = task.onExecute(mTaskOperation);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            // Set the status of the task.
            task.setStatus(Task.Status.FINISHED);

            // Print the task state.
            this.printExecuteTaskState(task);
        }
    }

    /**
     * Post the progress, it will call  {@link Task#onProgressUpdate(Object progresses)} method.
     *
     * @param progresses
     */
    private void postProgress(Object progresses)
    {
        if (null != mCurTask)
        {
            mCurTask.onProgressUpdate(progresses);
        }
    }

    /**
     * Perform the state change.
     *
     * @param oldState
     * @param newState
     */
    private void performStateChange(final State oldState, final State newState)
    {
        if (null != mListener)
        {
            mUIHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mListener.onStateChanged(TaskManager.this, oldState, newState);
                }
            });
        }
    }

    /**
     * Print the task manager information.
     *
     * @param oldState
     * @param newState
     */
    private void printTaskManagerState(final State oldState, final State newState)
    {
        Log.d("TaskManager", "TaskManager state changed, task manager = " + this.toString());
    }

    /**
     * Push the task manager to the list.
     *
     * @param taskManager
     */
    private static void pushTaskManager(TaskManager taskManager)
    {
        if (null != taskManager)
        {
            String name = taskManager.getName();
            if (!TextUtils.isEmpty(name))
            {
                s_taskManagers.put(name, taskManager);
            }
        }
    }

    /**
     * Pop the task manager from the list.
     * @param taskManager
     */
    private static void popTaskManager(TaskManager taskManager)
    {
        if (null != taskManager)
        {
            String name = taskManager.getName();
            s_taskManagers.remove(name);
        }
    }
}
