class Solution {
    public int leastInterval(char[] tasks, int n) {
        //for every n + 1 interval, put the task in from the highest to lowest task numbers.
        //So there are just two situations.
        //First, all the task can be executed without idle. --> res = tasks.lengt;
        //Second, some of the task has too many so that all the other tasks can not fill all the idle interval.
        //in the second situation. res = the task with most times add all the idle interval.
        //all the tasks with maxCount will fill in the last interval.
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : tasks) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        int max = 0;
        int maxNum = 0;
        for (char c : count.keySet()) {
            if (count.get(c) > max) {
                max = count.get(c);
                maxNum = 1;
            }
            else if (count.get(c) == max) {
                maxNum++;
            }
        }
        int left = tasks.length - max;
        int space = n * max;
        if (space < left) {
            return tasks.length;
        }
        return max * (n + 1) - (n + 1 - maxNum);
    }
}