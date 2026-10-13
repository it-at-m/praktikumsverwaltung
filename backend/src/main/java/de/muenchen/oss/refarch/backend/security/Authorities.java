package de.muenchen.oss.refarch.backend.security;

/// Each possible authority in this project is represented by a constant in this class.
///
/// The constants are used within the [org.springframework.stereotype.Controller] or
/// [org.springframework.stereotype.Service] classes in the method security annotations (e.g.
/// [org.springframework.security.access.prepost.PreAuthorize]).
@SuppressWarnings("PMD.DataClass")
public final class Authorities {

    public static final String ADMIN_ROLE = "hasAnyRole('admin')";
    public static final String ADMIN_OR_LEGIT_USER = "hasRole('admin') or hasRole('student') and #id == @authUtils.getUserNumber()";
    public static final String ADMIN_OR_LEGIT_USER_OR_LEGIT_FACHUSER = "hasRole('admin') or hasRole('student') and #id == @authUtils.getUserNumber() or hasRole('fachstudent') and #id == @authUtils.getUserNumber()";
    public static final String ADMIN_OR_LEGIT_FACHUSER = "hasRole('admin') or hasRole('fachstudent') and #id == @authUtils.getUserNumber()";

    public static final class Student {
        public static final String GET_ALL = ADMIN_ROLE;
        public static final String GET_ONE_BY_ID = ADMIN_OR_LEGIT_USER_OR_LEGIT_FACHUSER;
        public static final String GET_SEARCH = ADMIN_ROLE;
        public static final String DELETE_BY_ID = ADMIN_ROLE;
        public static final String PUT_BY_ID = ADMIN_ROLE;
        public static final String POST = ADMIN_ROLE;

    }

    public static final class Praktikum {
        public static final String GET_ONE_BY_ID = ADMIN_OR_LEGIT_USER_OR_LEGIT_FACHUSER;
        public static final String POST = ADMIN_ROLE;
        public static final String PUT_BY_ID = ADMIN_ROLE;
        public static final String DELETE_BY_ID = ADMIN_ROLE;
    }

    public static final class Studiengang {
        public static final String GET_ALL = ADMIN_ROLE;
        public static final String DELETE_BY_ID = ADMIN_ROLE;
        public static final String POST = ADMIN_ROLE;
    }

    public static final class Studium {
        public static final String PUT = ADMIN_ROLE;
        public static final String DELETE = ADMIN_ROLE;
        public static final String GET_BY_ID = ADMIN_ROLE;
    }

    public static final class Taetigkeitenblock {
        public static final String GET_BY_ID_AND_DAY = ADMIN_OR_LEGIT_USER_OR_LEGIT_FACHUSER;
        //public static final String POST = ADMIN_OR_LEGIT_USER_OR_LEGIT_FACHUSER;
        public static final String POST = "hasRole('admin') or hasRole('student') and #dto.studentId == @authUtils.getUserNumber() or hasRole('fachstudent') and #dto.studentId == @authUtils.getUserNumber()";
        public static final String PUT = "hasRole('admin') or hasRole('student') and #studentId == @authUtils.getUserNumber() or hasRole('fachstudent') and #studentId == @authUtils.getUserNumber()";
        public static final String DELETE = "hasRole('admin') or hasRole('student') and #studentId == @authUtils.getUserNumber() or hasRole('fachstudent') and #studentId == @authUtils.getUserNumber()";
    }

    public static final class Zeitgutschrift {
        public static final String POST = "hasRole('admin') or hasRole('fachstudent') and authUtils.getUserNumber() == #body.praktikumId";
        public static final String PUT = "hasRole('admin') or hasRole('fachstudent') and authUtils.getUserNumber() == #body.praktikumId";
        public static final String DELETE_BY_ID = "hasRole('admin') or hasRole('fachstudent') and @ZeitgutschriftService.isOwnedBy(@authUtils.getUserNumber, #id)";
    }

    private Authorities() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
