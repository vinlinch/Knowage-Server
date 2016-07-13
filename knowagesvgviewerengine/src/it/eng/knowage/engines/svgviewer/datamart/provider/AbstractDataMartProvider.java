package it.eng.knowage.engines.svgviewer.datamart.provider;

import it.eng.knowage.engines.svgviewer.SvgViewerEngineException;
import it.eng.knowage.engines.svgviewer.component.AbstractSvgViewerEngineComponent;
import it.eng.knowage.engines.svgviewer.datamart.provider.configurator.AbstractDataMartProviderConfigurator;
import it.eng.knowage.engines.svgviewer.dataset.DataMart;
import it.eng.knowage.engines.svgviewer.dataset.HierarchyMember;
import it.eng.knowage.engines.svgviewer.dataset.provider.Hierarchy;
import it.eng.spago.base.SourceBean;
import it.eng.spagobi.utilities.assertion.Assert;

import java.util.Map;
import java.util.Set;

/**
 * The Class AbstractDatasetProvider.
 *
 */
public class AbstractDataMartProvider extends AbstractSvgViewerEngineComponent implements IDataMartProvider {

	/** The Hierarchies Members */
	private Map<String, HierarchyMember> hierarchyMembers;

	/** The hierarchies. */
	private Map hierarchies;

	/** The selected hierarchy name. */
	private String selectedHierarchyName;

	/** The selected level. */
	private String selectedLevel;

	/** The selected member name. */
	private String selectedMemberName;

	/** The parent value used to filter the dataset (optional) */
	private String selectedParentName;

	/**
	 * Instantiates a new abstract dataset provider.
	 */
	public AbstractDataMartProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.AbstractGeoEngineComponent#init(java.lang.Object)
	 */
	@Override
	public void init(Object conf) throws SvgViewerEngineException {
		super.init(conf);
		AbstractDataMartProviderConfigurator.configure(this, getConf());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getDataSet()
	 */
	@Override
	public DataMart getDataMart() throws SvgViewerEngineException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getDataDetails(java.lang.String)
	 */
	@Override
	public SourceBean getDataDetails(String filterValue) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getHierarchyNames()
	 */
	@Override
	public Set getHierarchyMembersNames() {
		if (hierarchyMembers != null) {
			return hierarchyMembers.keySet();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getHierarchy(java.lang.String)
	 */
	@Override
	public Hierarchy getHierarchy(String name) {
		if (hierarchies != null) {
			return (Hierarchy) hierarchies.get(name);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getSelectedHierarchy()
	 */
	@Override
	public Hierarchy getSelectedHierarchy() {
		if (hierarchies != null) {
			return (Hierarchy) hierarchies.get(selectedHierarchyName);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getSelectedLevel()
	 */
	// @Override
	// public Hierarchy.Level getSelectedLevel() {
	// Hierarchy selectedHierarchy = getSelectedHierarchy();
	// if (selectedHierarchy != null) {
	// return selectedHierarchy.getLevel(selectedLevel);
	// }
	// return null;
	// }

	/**
	 * Sets the hierarchies.
	 *
	 * @param hierarchies
	 *            the new hierarchies
	 */
	public void setHierarchies(Map hierarchies) {
		this.hierarchies = hierarchies;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getSelectedHierarchyName()
	 */
	@Override
	public String getSelectedHierarchyName() {
		return selectedHierarchyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#setSelectedHierarchyName(java.lang.String)
	 */
	@Override
	public void setSelectedHierarchyName(String selectedHierarchyName) {
		this.selectedHierarchyName = selectedHierarchyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#getSelectedLevelName()
	 */
	@Override
	public String getSelectedLevel() {
		return selectedLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.spagobi.engines.geo.dataset.provider.IDatasetProvider#setSelectedLevel(java.lang.String)
	 */
	@Override
	public void setSelectedLevel(String selectedLevel) {
		this.selectedLevel = selectedLevel;
	}

	/**
	 * @return the hierarchyMembers
	 */
	public Map<String, HierarchyMember> getHierarchyMembers() {
		return hierarchyMembers;
	}

	/**
	 * @param hierarchyMembers
	 *            the hierarchyMembers to set
	 */
	public void setHierarchyMembers(Map<String, HierarchyMember> hierarchyMembers) {
		this.hierarchyMembers = hierarchyMembers;
	}

	/**
	 * Gets the hierarchy member by name.
	 *
	 * @param name
	 *            the member name
	 *
	 * @return the hierarchy member
	 */
	@Override
	public HierarchyMember getHierarchyMember(String name) {
		HierarchyMember toReturn = this.hierarchyMembers.get(name);
		Assert.assertNotNull(toReturn, "Hierarchy Member [" + name + "] cannot be null");
		return toReturn;
	}

	@Override
	public String getSelectedMemberName() {
		return selectedMemberName;
	}

	@Override
	public void setSelectedMemberName(String selectedMemberName) {
		this.selectedMemberName = selectedMemberName;
	}

	public String getSelectedParentName() {
		return selectedParentName;
	}

	public void setSelectedParentName(String selectedParentName) {
		this.selectedParentName = selectedParentName;
	}

}
