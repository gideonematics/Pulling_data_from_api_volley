package iconium.com.pulling_data_from_api_volley;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ogie on 9/14/2017.
 */

public class ApiDevelopersPayLoadResolver {
//
//
//    @SerializedName("total_count")
//    @Expose
//    private Integer totalCount;
//    @SerializedName("incomplete_results")
//    @Expose
//    private Boolean incompleteResults;
//    @SerializedName("items")
//    @Expose
//    private ArrayList<Developer> items = null;
//
//    public Integer getTotalCount() {
//        return totalCount;
//    }
//
//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }
//
//    public Boolean getIncompleteResults() {
//        return incompleteResults;
//    }
//
//    public void setIncompleteResults(Boolean incompleteResults) {
//        this.incompleteResults = incompleteResults;
//    }
//
//    public ArrayList<Developer> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<Developer> items) {
//        this.items = items;
//    }
//


    String total_counts;
    String incomplete_results;

    @SerializedName("items")
    @Expose
    ArrayList<Developer> items ;

    public String getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(String total_counts) {
        this.total_counts = total_counts;
    }

    public String getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(String incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Developer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Developer> items) {
        this.items = items;
    }



}
