package com.utamatisi.app.models.domain;

import com.utamatisi.app.models.milestone.BusinessDateMilestonedImpl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "${class}")
@NamedQueries({
@NamedQuery(name = "${className}.findAll", query = ${className}.GENERIC_SELECT)
})
public class ${className}  extends BusinessDateMilestonedImpl {

    <#list fields as field>
    public static String ${field};
    </#list>

    public ${className}() {
    }
    public ${className}(<#list fields as field>String ${field},</#list>) {
<#list fields as field>
        this.${field} = ${field};
</#list>
    }
<#list fields as field>
    public void set${field}(String ${field})
    {
        this.${field} = ${field};
    }
    public String get${field}()
    {
        return this.${field};
    }
</#list>
}
