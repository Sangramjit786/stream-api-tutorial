package net.javaguides.sys.design.gym_membership;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

enum MembershipStatus {
    /*
      Membership Status is of three types: BRONZE, SILVER and GOLD.
      BRONZE is the default membership a new member gets.
      SILVER and GOLD are paid memberships for the gym.
    */
    BRONZE,
    SILVER,
    GOLD
}

class Workout {
    /**
     * This class represents a single workout session for a member.
     * Each object of the Workout class has a unique ID, as well as
     * a start time and end time that are represented in the number
     * of minutes spent from the start of the day.
     */

    private int id;
    private int startTime;
    private int endTime;

    public Workout(int id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return endTime - startTime;
    }
}

class Member {
    /* Data about a gym member.*/
    public int memberId;
    public String name;
    public MembershipStatus membershipStatus;
    List<Workout> workouts = new ArrayList<>();

    public Member(int memberId, String name, MembershipStatus membershipStatus) {
        this.memberId = memberId;
        this.name = name;
        this.membershipStatus = membershipStatus;
    }

    void addingWorkout(Workout workout){
        this.workouts.add(workout);
    }

    List<Workout> getWorkouts(){
        return this.workouts;
    }

    int getId(){
        return this.memberId;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name + ", Membership Status: " + membershipStatus;
    }
}

class Membership {
    /*
      Data for managing a gym membership, and methods which staff can
      use to perform any queries or updates.
    */
    public List<Member> members;

    public Membership() {
        members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void updateMembership(int memberId, MembershipStatus membershipStatus) {
        for (Member member : members) {
            if (member.memberId == memberId) {
                member.membershipStatus = membershipStatus;
                break;
            }
        }
    }

    public MembershipStatistics getMembershipStatistics() {
        int totalMembers = members.size();
        int totalPaidMembers = 0;
        for (Member member : members) {
            if ((member.membershipStatus == MembershipStatus.GOLD) || (member.membershipStatus ==MembershipStatus.SILVER)) {
                totalPaidMembers++;
            }
        }
        double conversionRate = (totalPaidMembers / (double) totalMembers) * 100.0;
        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
    }

    boolean addWorkout(int id, Workout workout){
        for(Member member : members){
            if(id == member.getId()){
                member.addingWorkout(workout);
                return true;
            }
            continue;
        }
        return false;
    }

    Map<Integer, Double> getAverageWorkoutDurations(){
        Map<Integer,Double> avgWorkoutDuration = new HashMap<>();

        for(Member member : members){
            int totalDuration = 0;
            List<Workout> workouts = member.getWorkouts();

            if(workouts == null || workouts.isEmpty()){
                avgWorkoutDuration.putIfAbsent(member.getId(), null);
            } else {
                for(Workout workout : workouts){
                    totalDuration += workout.getEndTime() - workout.getStartTime();
                }

                avgWorkoutDuration.putIfAbsent(member.getId(), totalDuration / (double) workouts.size());
            }


        }
        return avgWorkoutDuration;
    }

    Map<Integer, Integer> getDuePayments() {

        Map<Integer, Integer> duePayments = new HashMap<>();

        for (Member member : members) {

            List<Workout> workouts = member.getWorkouts();

            // No workouts
            if (workouts == null || workouts.isEmpty()) {
                duePayments.put(member.getId(), 0);
                continue;
            }

            // Sort workouts by ID
            workouts.sort(Comparator.comparingInt(Workout::getId));

            int freeWorkouts = 0;
            int ratePerHour = 0;

            switch (member.membershipStatus) {

                case BRONZE:
                    freeWorkouts = 1;
                    ratePerHour = 10;
                    break;

                case SILVER:
                    freeWorkouts = 3;
                    ratePerHour = 8;
                    break;

                case GOLD:
                    freeWorkouts = 5;
                    ratePerHour = 6;
                    break;
            }

            int totalPayment = 0;

            for (int i = freeWorkouts; i < workouts.size(); i++) {

                Workout workout = workouts.get(i);

                int durationMinutes = workout.getDuration();

                // Round up to nearest hour
                int hours = (int) Math.ceil(durationMinutes / 60.0);

                totalPayment += hours * ratePerHour;
            }

            duePayments.put(member.getId(), totalPayment);
        }

        return duePayments;
    }

}

class MembershipStatistics {
    /*
      Class for returning the getMembershipStatistics result
    */
    public int totalMembers;
    public int totalPaidMembers;
    public double conversionRate;

    public MembershipStatistics(int totalMembers, int totalPaidMembers, double conversionRate) {
        this.totalMembers = totalMembers;
        this.totalPaidMembers = totalPaidMembers;
        this.conversionRate = conversionRate;
    }
}

public class Solution {
    /*
      This is not a complete test suite, but tests some basic functionality of
      the code and shows how to use it.
    */
    public static void main(String[] args) {
        testMember();
        testMembership();
        testAddWorkout();
        testGetAverageWorkoutDurations();
        testGetDuePayments();
    }

    public static void testMember() {
        System.out.println("Running testMember");
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        assert testMember.memberId == 1 :
                "Member ID should be 1, was " + testMember.memberId;
        assert testMember.name.equals("John Doe") :
                "Member name should be \"John Doe\", was \"" + testMember.name + "\"";
        assert testMember.membershipStatus == MembershipStatus.BRONZE :
                "Membership status should be BRONZE, was " + testMember.membershipStatus;
    }

    public static void testMembership() {
        System.out.println("Running testMembership");
        Membership testMembership = new Membership();
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        testMembership.addMember(testMember);
        assert testMembership.members.size() == 1 :
                "Members size should be 1, was " + testMembership.members.size();
        assert testMembership.members.get(0).equals(testMember) :
                "First member should equal testMember";

        testMembership.updateMembership(1, MembershipStatus.SILVER);
        assert testMembership.members.get(0).membershipStatus == MembershipStatus.SILVER :
                "Membership status should be SILVER, was " + testMembership.members.get(0).membershipStatus;

        Member testMember2 = new Member(2, "Alex C", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Member testMember3 = new Member(3, "Marie C", MembershipStatus.GOLD);
        testMembership.addMember(testMember3);

        Member testMember4 = new Member(4, "Joe D", MembershipStatus.SILVER);
        testMembership.addMember(testMember4);

        Member testMember5 = new Member(5, "June R", MembershipStatus.BRONZE);
        testMembership.addMember(testMember5);

        Member testMember6 = new Member(6, "Westley D", MembershipStatus.SILVER);
        testMembership.addMember(testMember6);

        MembershipStatistics attendanceStats = testMembership.getMembershipStatistics();
        assert attendanceStats.totalMembers == 6 :
                "Total members should be 6, was " + attendanceStats.totalMembers;
        assert attendanceStats.totalPaidMembers == 4 :
                "Total paid members should be 4, was " + attendanceStats.totalPaidMembers;
        assert Math.abs(attendanceStats.conversionRate - 66.67) < 0.1 :
                "Conversion rate should be 66.67, was " + attendanceStats.conversionRate;
    }

    public static void testAddWorkout() {
        System.out.println("Running testAddWorkout");
        Membership testMembership = new Membership();
        Member testMember1 = new Member(12, "John Doe", MembershipStatus.SILVER);
        testMembership.addMember(testMember1);

        Member testMember2 = new Member(22, "Alex Cleeve", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Workout testWorkout1 = new Workout(111, 10, 20);
        Workout testWorkout2 = new Workout(112, 15, 35);
        Workout testWorkout3 = new Workout(113, 20, 25);
        Workout testWorkout99 = new Workout(999, 1, 2);

        assertTrue(testMembership.addWorkout(12, testWorkout1));
        assertTrue(testMembership.addWorkout(22, testWorkout2));
        assertTrue(testMembership.addWorkout(12, testWorkout3));
        assertFalse(testMembership.addWorkout(404, testWorkout99));
    }

    public static void testGetAverageWorkoutDurations() {
        System.out.println("Running testGetAverageWorkoutDurations");
        Membership testMembership = new Membership();
        Member testMember1 = new Member(12, "John Doe", MembershipStatus.SILVER);
        testMembership.addMember(testMember1);

        Member testMember2 = new Member(22, "Alex Cleeve", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Member testMember3 = new Member(31, "Marie Cardiff", MembershipStatus.GOLD);
        testMembership.addMember(testMember3);

        Member testMember4 = new Member(37, "George Costanza", MembershipStatus.SILVER);
        testMembership.addMember(testMember4);

        Workout testWorkout1 = new Workout(101, 10, 20);
        Workout testWorkout2 = new Workout(102, 15, 35);
        Workout testWorkout3 = new Workout(103, 45, 90);
        Workout testWorkout4 = new Workout(104, 100, 155);
        Workout testWorkout5 = new Workout(105, 120, 200);
        Workout testWorkout6 = new Workout(106, 300, 400);
        Workout testWorkout7 = new Workout(107, 1000, 1010);
        Workout testWorkout8 = new Workout(108, 1010, 1045);

        testMembership.addWorkout(12, testWorkout1);
        testMembership.addWorkout(22, testWorkout2);
        testMembership.addWorkout(31, testWorkout3);
        testMembership.addWorkout(12, testWorkout4);
        testMembership.addWorkout(22, testWorkout5);
        testMembership.addWorkout(31, testWorkout6);
        testMembership.addWorkout(12, testWorkout7);
        testMembership.addWorkout(404, testWorkout8);

        Map<Integer, Double> averageDurations = testMembership.getAverageWorkoutDurations();
        assert Math.abs(averageDurations.get(12) - 25.0) < 0.1 :
                "average duration for member 12 should be 25.0, was " + averageDurations.get(12);
        assert Math.abs(averageDurations.get(22) - 50.0) < 0.1 :
                "average duration for member 22 should be 50.0, was " + averageDurations.get(22);
        assert Math.abs(averageDurations.get(31) - 72.5) < 0.1 :
                "average duration for member 31 should be 72.5, was " + averageDurations.get(31);
        assert averageDurations.get(37) == null : "average for a member with no workouts should be null";
        assertFalse(averageDurations.containsKey(404));
    }

    public static void testGetDuePayments() {
        System.out.println("Running testGetDuePayments");
        // Test get_due_payments function
        Membership testMembership = new Membership();
        testMembership.addMember(new Member(1, "John Doe", MembershipStatus.BRONZE));
        testMembership.addMember(new Member(2, "Alex C", MembershipStatus.SILVER));
        testMembership.addMember(new Member(3, "Marie C", MembershipStatus.GOLD));

        // Add workouts for members
        Map<Integer, List<Workout>> memberWorkouts = new HashMap<>();
        memberWorkouts.put(1, Arrays.asList(
                new Workout(1, 500, 700), new Workout(10, 300, 350), new Workout(12, 10, 20),
                new Workout(3, 50, 90), new Workout(6, 130, 150), new Workout(15, 900, 920)
        ));
        memberWorkouts.put(2, Arrays.asList(
                new Workout(13, 510, 540), new Workout(14, 600, 700), new Workout(2, 15, 35),
                new Workout(4, 100, 155), new Workout(18, 200, 225), new Workout(8, 1050, 1155)
        ));
        memberWorkouts.put(3, Arrays.asList(
                new Workout(5, 120, 135), new Workout(17, 140, 190), new Workout(9, 210, 255),
                new Workout(11, 400, 450), new Workout(16, 910, 940), new Workout(7, 1000, 1100)
        ));

        for (Map.Entry<Integer, List<Workout>> entry : memberWorkouts.entrySet()) {
            int memberId = entry.getKey();
            List<Workout> workoutList = entry.getValue();
            for (Workout workout : workoutList) {
                testMembership.addWorkout(memberId, workout);
            }
        }

        Map<Integer, Integer> duePayments = testMembership.getDuePayments();
        assert duePayments.get(1) == 50 :
                "due payment for member 1 should be 50, was " + duePayments.get(1);
        assert duePayments.get(2) == 32 :
                "due payment for member 2 should be 32, was " + duePayments.get(2);
        assert duePayments.get(3) == 6 :
                "due payment for member 3 should be 6, was " + duePayments.get(3);

        // Test member with no workouts
        testMembership.addMember(new Member(4, "Ron Burgundy", MembershipStatus.SILVER));
        Map<Integer, Integer> duePayments2 = testMembership.getDuePayments();
        assert duePayments2.get(4) == 0 :
                "due payment for member 4 should be 0, was " + duePayments2.get(4);
    }
}
