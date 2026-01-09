public class VIPCustomer extends Customer {
    private String membershipLevel;
    private boolean hasPersonalStylist;

    public VIPCustomer(int customerId, String name, String preferredSize,
                       int points, String membershipLevel, boolean hasPersonalStylist) {
        super(customerId, name, preferredSize, points);
        this.membershipLevel = membershipLevel;
        this.hasPersonalStylist = hasPersonalStylist;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel != null && !membershipLevel.trim().isEmpty()) {
            this.membershipLevel = membershipLevel;
        }
    }

    public boolean hasPersonalStylist() {
        return hasPersonalStylist;
    }

    public void setHasPersonalStylist(boolean hasPersonalStylist) {
        this.hasPersonalStylist = hasPersonalStylist;
    }

    @Override
    public void shop() {
        String stylistInfo = hasPersonalStylist ? " with personal stylist" : "";
        System.out.println("VIP customer " + name + " (" + membershipLevel +
                " member) is shopping" + stylistInfo + ".");
    }

    @Override
    public String getCustomerType() {
        return "VIP Customer";
    }

    @Override
    public double getDiscount() {
        switch (membershipLevel.toLowerCase()) {
            case "gold": return 15.0;
            case "platinum": return 20.0;
            case "diamond": return 25.0;
            default: return 10.0;
        }
    }

    public void requestPersonalStylist() {
        if (hasPersonalStylist) {
            System.out.println(name + " already has a personal stylist assigned.");
        } else {
            hasPersonalStylist = true;
            System.out.println("Personal stylist assigned to " + name + "!");
        }
    }

    public void upgradeMembership() {
        if (membershipLevel.equalsIgnoreCase("Gold")) {
            membershipLevel = "Platinum";
            System.out.println(name + " upgraded to Platinum membership! 🎉");
        } else if (membershipLevel.equalsIgnoreCase("Platinum")) {
            membershipLevel = "Diamond";
            System.out.println(name + " upgraded to Diamond membership! 💎");
        } else {
            System.out.println(name + " is already at the highest level (Diamond)! 👑");
        }
    }

    public boolean isDiamondMember() {
        return membershipLevel.equalsIgnoreCase("Diamond");
    }

    @Override
    public String toString() {
        String stylistStatus = hasPersonalStylist ? "Yes" : "No";
        return super.toString() + " | Level: " + membershipLevel +
                " | Personal Stylist: " + stylistStatus;
    }
}
