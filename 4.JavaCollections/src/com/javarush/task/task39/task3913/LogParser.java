package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private Path logDir;


    public LogParser(Path logDir) {
        this.logDir = logDir;
    }


    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> setIp = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date)) {
                setIp.add(u.ip);
            }
        }

        return setIp;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> userIps = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.name.equals(user)) {
                userIps.add(u.ip);
            }
        }
        return userIps;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> eventIps = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.event != null && u.event.contains(event.toString())) {
                eventIps.add(u.ip);
            }
        }

        return eventIps;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> statusIps = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.status != null && u.status.contains(status.toString())) {
                statusIps.add(u.ip);
            }
        }

        return statusIps;
    }

    public List<String> getAllFiles(Path path) {
        List<String> fileList = new ArrayList<>();

        File file = path.toFile();

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                getAllFiles(f.toPath());
            } else if (f.isFile() & f.getName().endsWith("log")) {
                FileReader fileReader;
                try {
                    fileReader = new FileReader(f);
                    BufferedReader buff = new BufferedReader(fileReader);

                    String line;

                    while ((line = buff.readLine()) != null) {
                        fileList.add(line);
                    }

                    fileReader.close();
                    buff.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return fileList;
    }

    public List<User> parseStringList(Path path) {
        List<User> users = new ArrayList<>();
        List<String> getAllFiles = getAllFiles(logDir);
        for (String s : getAllFiles) {
            String[] strings = s.split("\t");
            String ip = strings[0];
            String name = strings[1];
            Date date = findDate(strings[2]);
            String event = findEvent(strings[3]);
            String status = findStatus(strings[4]);
            users.add(new User(ip, name, date, event, status));
        }

        return users;
    }


    public Date findDate(String line) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            date = sdf.parse(line);
        } catch (ParseException e) {

        }

        return date;
    }

    public String findEvent(String line) {
        String event = null;
        for (Event e : Event.values()) {
            if (line.contains(e.toString())) {
                event = line;
            }
        }
        return event;
    }

    public Event changeString(String line) {
        Event event = null;
        for (Event e : Event.values()) {
            if (line.contains(e.toString())) {
                event = e;
            }
        }
        return event;
    }

    public String findStatus(String line) {
        String status = null;
        for (Status s : Status.values()) {
            if (line.contains(s.toString())) {
                status = s.toString();
            }
        }
        return status;
    }

    public boolean checkDate(Date after, Date before, Date date) {
        if (after == null) {
            if (before == null) return true;
            else return date.before(before);
        } else {
            if (before == null) return date.after(after);
        }

        return date.after(after) && date.before(before);
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            usersName.add(u.name);
        }

        return usersName;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date)) {
                usersName.add(u.name);
            }
        }
        return usersName.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<String> userEvents = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (u.name.equals(user) && checkDate(after, before, u.date)) {
                userEvents.add(u.event);
            }
        }
        return userEvents.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (u.ip.equals(ip) && checkDate(after, before, u.date)) {
                usersName.add(u.name);
            }
        }
        return usersName;

    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (u.event.equals(Event.LOGIN.toString()) && checkDate(after, before, u.date)) {
                usersName.add(u.name);
            }
        }
        return usersName;

    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (u.event.equals(Event.DOWNLOAD_PLUGIN.toString()) && checkDate(after, before, u.date)) {
                usersName.add(u.name);
            }
        }
        return usersName;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (u.event.equals(Event.WRITE_MESSAGE.toString()) && checkDate(after, before, u.date)) {
                usersName.add(u.name);
            }
        }
        return usersName;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (u.event.contains(Event.SOLVE_TASK.toString()) && checkDate(after, before, u.date)) {
                usersName.add(u.name);
            }
        }
        return usersName;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (u.event.contains(Event.SOLVE_TASK.toString()) && u.event.contains(String.valueOf(task)) && checkDate(after, before, u.date)) {
                usersName.add(u.name);
            }
        }
        return usersName;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.event.contains(Event.DONE_TASK.toString())) {
                usersName.add(u.name);
            }
        }
        return usersName;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> usersName = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.event.contains(Event.DONE_TASK.toString()) && u.event.contains(String.valueOf(task))) {
                usersName.add(u.name);
            }
        }
        return usersName;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.event.contains(event.toString()) && u.name.equals(user)) {
                dates.add(u.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.status.contains(Status.FAILED.toString())) {
                dates.add(u.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.status.contains(Status.ERROR.toString())) {
                dates.add(u.date);
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date date = null;
        TreeSet<Date> dates = new TreeSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.name.equals(user) && u.event.contains(Event.LOGIN.toString())) {
                dates.add(u.date);
            }
        }
        if (!dates.isEmpty()) {
            date = dates.first();
        }
        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date date = null;
        TreeSet<Date> dates = new TreeSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.name.equals(user) && u.event.contains(Event.SOLVE_TASK.toString()) && u.event.contains(String.valueOf(task))) {
                dates.add(u.date);

            }
        }
        if (!dates.isEmpty()) {
            date = dates.first();
        }
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date date = null;
        TreeSet<Date> dates = new TreeSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.name.equals(user) && u.event.contains(Event.DONE_TASK.toString()) && u.event.contains(String.valueOf(task))) {
                dates.add(u.date);

            }
        }
        if (!dates.isEmpty()) {
            date = dates.first();
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.name.equals(user) && u.event.contains(Event.WRITE_MESSAGE.toString())) {
                dates.add(u.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.name.equals(user) && u.event.contains(Event.DOWNLOAD_PLUGIN.toString())) {
                dates.add(u.date);
            }
        }
        return dates;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        Set<String> strings = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date)) {
                strings.add(u.event);
            }
        }
        for (String s : strings) {
            events.add(changeString(s));
        }

        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        Set<String> strings = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.ip.equals(ip)) {
                strings.add(u.event);
            }
        }
        for (String s : strings) {
            events.add(changeString(s));
        }

        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        Set<String> strings = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.name.equals(user)) {
                strings.add(u.event);
            }
        }
        for (String s : strings) {
            events.add(changeString(s));
        }

        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        Set<String> strings = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.status.contains(Status.FAILED.toString())) {
                strings.add(u.event);
            }
        }
        for (String s : strings) {
            events.add(changeString(s));
        }

        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        Set<String> strings = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.status.contains(Status.ERROR.toString())) {
                strings.add(u.event);
            }
        }
        for (String s : strings) {
            events.add(changeString(s));
        }

        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        ArrayList<String> strings = new ArrayList<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.event.contains(Event.SOLVE_TASK.toString()) && u.event.contains(String.valueOf(task))) {
                strings.add(u.event);
            }
        }

        return strings.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        ArrayList<String> strings = new ArrayList<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(after, before, u.date) && u.event.contains(Event.DONE_TASK.toString()) && u.event.contains(String.valueOf(task))) {
                strings.add(u.event);
            }
        }

        return strings.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> taskMap = new HashMap<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            int numberOfTask = getNumberOfTask(u.event);
            if (checkDate(after, before, u.date) && u.event.contains(Event.SOLVE_TASK.toString()) && numberOfTask != 0) {
                if (!taskMap.containsKey(numberOfTask)) {
                    taskMap.put(numberOfTask, 1);
                } else {
                    int value = taskMap.get(numberOfTask);
                    taskMap.put(numberOfTask, value + 1);
                }
            }
        }

        return taskMap;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> taskMap = new HashMap<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            int numberOfTask = getNumberOfTask(u.event);
            if (checkDate(after, before, u.date) && u.event.contains(Event.DONE_TASK.toString()) && numberOfTask != 0) {
                if (!taskMap.containsKey(numberOfTask)) {
                    taskMap.put(numberOfTask, 1);
                } else {
                    int value = taskMap.get(numberOfTask);
                    taskMap.put(numberOfTask, value + 1);
                }
            }
        }

        return taskMap;
    }

    public int getNumberOfTask(String event) {
        int numberOfTask = 0;
        String substr = "";
        String solve = Event.SOLVE_TASK.toString();
        String done = Event.DONE_TASK.toString();

        if (event.contains(solve)) {
            substr = event.substring(solve.length());
            substr = substr.trim();
            numberOfTask = Integer.parseInt(substr);
        } else if (event.contains(done)) {
            substr = event.substring(done.length());
            substr = substr.trim();
            numberOfTask = Integer.parseInt(substr);
        }

        return numberOfTask;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<? extends Object> set = new HashSet<>();
        switch (query) {
            case "get ip":
                set = getUniqueIPs(null, null);
                break;
            case "get user":
                set = getAllUsers();
                break;
            case "get date":
                set = getAllDates();
                break;
            case "get event":
                set = getAllEvents(null, null);
                break;
            case "get status":
                set = getAllStatuses();
                break;
            default:
                Date first = null;
                Date last = null;
                if (query.contains("between")){
                    first = parseFirstDate(query);
                    last = parseLastDate(query);

                }
                if (query.contains("get ip for")) {
                    set = getIpFor(query, first, last);
                    break;
                }
                if (query.contains("get user for")) {
                    set = getUserFor(query, first, last);
                    break;
                }
                if (query.contains("get date for")) {
                    set = getDateFor(query, first, last);
                    break;
                }
                if (query.contains("get event for")) {
                    set = getEventFor(query, first, last);
                    break;
                }

                if (query.contains("get status for")) {
                    set = getStatusFor(query, first, last);
                    break;
                }
        }

        return (Set<Object>) set;
    }

    public Date parseFirstDate (String query){
        query = query.substring(query.indexOf("between") + 9, query.lastIndexOf("and") - 2);

        return findDate(query);

    }
    public Date parseLastDate (String query){
        query = query.substring(query.lastIndexOf("and") + 4);
        query = query.replaceAll("\"", "");

        return findDate(query);

    }

    public Set<Status> getStatusForIp(String ip, Date first, Date last) {
        Set<Status> statuses = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.ip.equals(ip)) statuses.add(changeStringStatus(u.status));
        }
        return statuses;
    }

    public Set<Status> getStatusForUser(String user, Date first, Date last) {
        Set<Status> statuses = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.name.equals(user)) statuses.add(changeStringStatus(u.status));
        }
        return statuses;
    }

    public Set<Status> getStatusForDate(Date date, Date first, Date last) {   ///?
        Set<Status> statuses = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.date.equals(date)) statuses.add(changeStringStatus(u.status));
        }
        return statuses;
    }

    public Set<Status> getStatusForEvent(Event event, Date first, Date last) {
        Set<Status> statuses = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.event.contains(event.toString())) statuses.add(changeStringStatus(u.status));
        }
        return statuses;
    }

    public Set<Object> getStatusFor(String query, Date first, Date last) {
        Set<? extends Object> set = new HashSet<>();
        String goalOfQuery = substringQuery(query);
        query = query.substring(0, query.indexOf("=")- 1);

        if (query.endsWith("ip")) {
            set = getStatusForIp(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("user")) {
            set = getStatusForUser(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("date")) {
            Date date1 = findDate(goalOfQuery);
            set = getStatusForDate(date1, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("event")) {
            Event event1 = changeString(goalOfQuery);
            set = getStatusForEvent(event1, first, last);
            return (Set<Object>) set;
        }

        return (Set<Object>) set;
    }


    public Set<Event> getEventsForDate(Date date, Date first, Date last) {   //?
        Set<Event> events = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.date.equals(date)) events.add(changeString(u.event));
        }
        return events;
    }

    public Set<Event> getEventsForStatus(Status status, Date first, Date last) {
        Set<Event> events = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.status.equals(status.toString())) events.add(changeString(u.event));
        }
        return events;
    }

    public Set<Object> getEventFor(String query, Date first, Date last) {
        Set<? extends Object> set = new HashSet<>();
        String goalOfQuery = substringQuery(query);
        query = query.substring(0, query.indexOf("=")- 1);

        if (query.endsWith("ip")) {
            set = getEventsForIP(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("user")) {
            set = getEventsForUser(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("date")) {
            Date date1 = findDate(goalOfQuery);
            set = getEventsForDate(date1, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("status")) {
            Status status1 = changeStringStatus(goalOfQuery);
            set = getEventsForStatus(status1, first, last);
            return (Set<Object>) set;
        }

        return (Set<Object>) set;
    }

    public String substringQuery(String query) {
        String subQuery = query.substring(query.indexOf("=") + 1);
        subQuery = subQuery.replaceAll("\"", "");
        subQuery = subQuery.replaceFirst(" ", "");
        if (query.contains("and")){
            subQuery = subQuery.substring(0, subQuery.indexOf("and") - 1);
        }

        return subQuery;
    }

    public Set<Date> getDatesForIp(String ip, Date first, Date last) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.ip.equals(ip)) dates.add(u.date);
        }
        return dates;
    }

    public Set<Date> getDatesForUser(String user, Date first, Date last) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.name.equals(user)) dates.add(u.date);
        }
        return dates;
    }

    public Set<Date> getDatesForEvent(Event event, Date first, Date last) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.event.contains(event.toString())) dates.add(u.date);
        }
        return dates;
    }

    public Set<Date> getDatesForStatus(Status status, Date first, Date last) {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.status.contains(status.toString())) dates.add(u.date);
        }
        return dates;
    }

    public Set<Object> getDateFor(String query, Date first, Date last) {
        Set<? extends Object> set = new HashSet<>();
        String goalOfQuery = substringQuery(query);
        query = query.substring(0, query.indexOf("=")- 1);

        if (query.endsWith("ip")) {
            set = getDatesForIp(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("user")) {
            set = getDatesForUser(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("event")) {
            Event event1 = changeString(goalOfQuery);
            set = getDatesForEvent(event1, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("status")) {
            Status status1 = changeStringStatus(goalOfQuery);
            set = getDatesForStatus(status1, first, last);
            return (Set<Object>) set;
        }

        return (Set<Object>) set;
    }

    public Set<String> getUsersForDate(Date date, Date first, Date last) {
        Set<String> userNames = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.date.equals(date)) userNames.add(u.name);
        }
        return userNames;
    }

    public Set<String> getUsersForEvent(Event event, Date first, Date last) {
        Set<String> userNames = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.event.contains(event.toString())) userNames.add(u.name);
        }
        return userNames;
    }

    public Set<String> getUsersForStatus(Status status, Date first, Date last) {
        Set<String> userNames = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.status.equals(status.toString())) userNames.add(u.name);
        }
        return userNames;
    }

    public Set<Object> getUserFor(String query, Date first, Date last) {
        Set<? extends Object> set = new HashSet<>();
        String goalOfQuery = substringQuery(query);
        query = query.substring(0, query.indexOf("=")- 1);

        if (query.endsWith("ip")) {
            set = getUsersForIP(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("date")) {
            Date date1 = findDate(goalOfQuery);
            set = getUsersForDate(date1, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("event")) {
            Event event1 = changeString(goalOfQuery);
            set = getUsersForEvent(event1, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("status")) {
            Status status1 = changeStringStatus(goalOfQuery);
            set = getUsersForStatus(status1, first, last);
            return (Set<Object>) set;
        }

        return (Set<Object>) set;
    }

    public Set<Object> getIpFor(String query, Date first, Date last) {
        Set<? extends Object> set = new HashSet<>();
        String goalOfQuery = substringQuery(query);
        query = query.substring(0, query.indexOf("=")- 1);

        if (query.endsWith("user")) {
            set = getIPsForUser(goalOfQuery, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("date")) {
            Date date1 = findDate(goalOfQuery);
            set = getIpsForDate(date1, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("event")) {
            Event event1 = changeString(goalOfQuery);
            set = getIPsForEvent(event1, first, last);
            return (Set<Object>) set;
        }

        if (query.endsWith("status")) {
            Status status1 = changeStringStatus(goalOfQuery);
            set = getIPsForStatus(status1, first, last);
            return (Set<Object>) set;
        }


        return (Set<Object>) set;
    }

    public Set<String> getIpsForDate(Date date, Date first, Date last) {
        Set<String> ips = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            if (checkDate(first, last, u.date) && u.date.getTime() == date.getTime()) ips.add(u.ip);
        }
        return ips;
    }

    public Set<Date> getAllDates() {
        Set<Date> dates = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            dates.add(u.date);
        }
        return dates;
    }

    public Set<Status> getAllStatuses() {
        Set<Status> statuses = new HashSet<>();
        List<User> users = parseStringList(logDir);
        for (User u : users) {
            statuses.add(changeStringStatus(u.status));
        }
        return statuses;
    }

    public Status changeStringStatus(String line) {
        Status status = null;
        for (Status s : Status.values()) {
            if (line.contains(s.toString())) {
                status = s;
            }
        }
        return status;
    }

    public class User {
        private String ip;
        private String name;
        private Date date;
        private String event;
        private String status;

        public User(String ip, String name, Date date, String event, String status) {
            this.ip = ip;
            this.name = name;
            this.date = date;
            this.event = event;
            this.status = status;
        }
    }


}