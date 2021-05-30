import csk from '../logos/csk.jpg';
import mi from '../logos/mi.jpg';
import rcb from '../logos/rcb.jpg';
import kkr from '../logos/kkr.jpg';
import srh from '../logos/srh.jpg';
import rr from '../logos/rr.jpg';
import dc from '../logos/dc.jpg';
import pk from '../logos/pk.jpg';
import dch from '../logos/dch.jpg';
import gl from '../logos/gl.jpg';
import kt from '../logos/kt.png';
import pw from '../logos/pw.jpg';
import rps from '../logos/rps.jpg';
import './TeamLogo.scss'

export const TeamLogo = ({teamName}) => {
    switch (teamName) {
        case 'Chennai Super Kings':
            return (<img className="logo" src={csk} alt="logo" />);
        case 'Mumbai Indians':
            return (<img className="logo" src={mi} alt="logo" />);
        case 'Royal Challengers Bangalore':
            return (<img className="logo" src={rcb} alt="logo" />);
        case 'Kolkata Knight Riders':
            return (<img className="logo" src={kkr} alt="logo" />);
        case 'Sunrisers Hyderabad':
            return (<img className="logo" src={srh} alt="logo" />);
        case 'Rajasthan Royals':
            return (<img className="logo" src={rr} alt="logo" />);
        case 'Delhi Capitals':
            return (<img className="logo" src={dc} alt="logo" />);
        case 'Kings XI Punjab':
            return (<img className="logo" src={pk} alt="logo" />);
        case 'Pune Warriors':
            return (<img className="logo" src={pw} alt="logo" />);
        case 'Gujarat Lions':
            return (<img className="logo" src={gl} alt="logo" />);
        case 'Kochi Tuskers Kerala':
            return (<img className="logo" src={kt} alt="logo" />);
        case 'Rising Pune Supergiants':
            return (<img className="logo" src={rps} alt="logo" />);
        case 'Deccan Chargers':
            return (<img className="logo" src={dch} alt="logo" />);
        default:
            return null;
    }
}