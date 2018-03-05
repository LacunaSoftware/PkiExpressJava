package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

class Version {

    private List<Integer> internalVersions;

    Version(String version) {

        if (version == null || version.length() <= 0) {
            throw new IllegalArgumentException("No string version was provided");
        }

        String[] versions = version.split("\\.");
        this.internalVersions = new ArrayList<Integer>();
        for (int i = 0; i < versions.length; i++) {
            this.internalVersions.add(Integer.parseInt(versions[i]));
        }
    }

    int compareTo(Version comparedVersion) {

        List<Integer> instanceInternalVersions = new ArrayList<Integer>(this.internalVersions);
        List<Integer> comparedInternalVersions = new ArrayList<Integer>(comparedVersion.getInternalVersions());

        while (instanceInternalVersions.size() != comparedInternalVersions.size()) {
            if (instanceInternalVersions.size() > comparedInternalVersions.size()) {
                comparedInternalVersions.add(0);
            } else {
                instanceInternalVersions.add(0);
            }
        }

        for (int i = 0; i < instanceInternalVersions.size(); i++) {
            if (instanceInternalVersions.get(i) > comparedInternalVersions.get(i)) {
                return 1;
            } else if (instanceInternalVersions.get(i) < comparedInternalVersions.get(i)) {
                return -1;
            }
        }

        return 0;
    }

    public List<Integer> getInternalVersions() {
        return internalVersions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.internalVersions.size() - 1; i++) {
            sb.append(this.internalVersions.get(i).toString());
            sb.append(".");
        }

        if (this.internalVersions.size() > 0) {
            sb.append(this.internalVersions.get(this.internalVersions.size() - 1));
        }

        return sb.toString();
    }
}
