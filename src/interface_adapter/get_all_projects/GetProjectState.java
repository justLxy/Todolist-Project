package interface_adapter.get_all_projects;

import entity.Project;

public class GetProjectState {
    private Project[] projects;

    public GetProjectState() {}

    public Project[] getProjects() {
        return projects;
    }

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }
}
