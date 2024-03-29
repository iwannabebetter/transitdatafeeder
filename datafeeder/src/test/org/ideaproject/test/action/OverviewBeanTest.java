/**
 *  Copyright 2010 SingleMind Consulting, Inc. (http://singlemindconsulting.com)
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */
package org.ideaproject.test.action;

import org.ideaproject.action.OverviewBean;
import org.ideaproject.action.entityhome.AgencyHome;
import org.ideaproject.test.AbstractAdminLoginSeamTest;
import org.ideaproject.test.TestConstants;
import org.jboss.seam.annotations.Transactional;
import org.testng.annotations.Test;

/**
 * @author dirk
 *
 */
public class OverviewBeanTest extends AbstractAdminLoginSeamTest {

	@Test
	@Transactional
	public void testViewBeanFunctions() throws Exception {
		loginAdmin();

		new FacesRequest() {
			@Override
			protected void invokeApplication() throws Exception {
				AgencyHome agencyHome = (AgencyHome) getInstance("agencyHome");
				OverviewBean overviewBean = (OverviewBean) getInstance("overviewBean");

				agencyHome.setAgencyAgencyId(TestConstants.DEFAULT_AGENCY_ID);
				agencyHome.load();

				assert overviewBean.getAllFaresCount() > 0;
				assert overviewBean.getAllRoutesCount() > 0;
				assert overviewBean.getAllStopsCount() > 0;
				assert overviewBean.getAllTripsCount() > 0;
				assert overviewBean.getNonLocatedStopsCount() == 0;
				assert overviewBean.getNoStopTimesTripsCount() == 0;
				assert overviewBean.getNoTripsRoutesCount() == 0;
				assert overviewBean.getScheduleGroups().size() > 0;
				assert overviewBean.getUnscheduledStopsCount() == 0;
				assert overviewBean.getUnusedFaresCount() == 0;
				assert overviewBean.getUpcomingHolidays().size() > 0;

				// Now test lazy loaded values
				assert overviewBean.getAllFaresCount() > 0;
				assert overviewBean.getAllRoutesCount() > 0;
				assert overviewBean.getAllStopsCount() > 0;
				assert overviewBean.getAllTripsCount() > 0;
				assert overviewBean.getNonLocatedStopsCount() == 0;
				assert overviewBean.getNoStopTimesTripsCount() == 0;
				assert overviewBean.getNoTripsRoutesCount() == 0;
				assert overviewBean.getScheduleGroups().size() > 0;
				assert overviewBean.getUnscheduledStopsCount() == 0;
				assert overviewBean.getUnusedFaresCount() == 0;
				assert overviewBean.getUpcomingHolidays().size() > 0;
			}
		}.run();
	}
}
